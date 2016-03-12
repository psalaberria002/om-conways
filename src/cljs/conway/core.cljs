(ns conway.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [timeout <! >! alts! chan]])
  (:require-macros [cljs.core.async.macros :refer [go go-loop]]))


(enable-console-print!)

(def cell-width 10)
(def cell-height 10)

;; Initial state, the "Blinker" lifeform
(defonce app-state (atom {:old-state #{}
                          :game-state #{[-1 0] [-1 1]
                                        [0 -1] [0 0] [1 0]}}))


(defn neighbors
  "Given a cell, return the collection of its neighbors in every direction"
  [[x y]]
  (for [dx [-1 0 1]
        dy [-1 0 1]
        :when (not= [dx dy] [0 0])]
    [(+ x dx) (+ y dy)]))

(defn next-population
  "Given a population, return the next generation of it
   following the rules of Conway's Life"
  [pop]
  (let [all-neighbors (mapcat neighbors pop)
        neigh-count (frequencies all-neighbors)]
    (set (for [[cell count] neigh-count
               :when (or (= count 3)
                         (and (= count 2)
                              (pop cell)))]
           cell))))

(defn grid-component
  [app]
  (reify
    om/IRender
    (render [_]
      (dom/svg #js {:width 450
                    :height 450
                    :viewBox "-450 -450 900 900"}
               (for [[x y] (:game-state app)]
                 (dom/rect #js {:width cell-width
                                :height cell-height
                                :fill (if ((:old-state app) [x y])
                                        "red"
                                        "green")
                                :x (* x cell-width)
                                :y (* y cell-height)}))))))

(defn app-component
  [app owner]
  (let [evolve-fn (fn []
                    (om/set-state! owner :evolving true)
                    (let [command-ch (om/get-state owner :death-ch)]
                      (go-loop []
                               (let [[value from-ch] (alts! [command-ch (timeout 100)])]
                                 (if (and (= from-ch command-ch)
                                          (= value :death))
                                   (om/set-state! owner :evolving false)
                                   (do (om/update! app :old-state (:game-state @app))
                                       (om/transact! app :game-state next-population)
                                       (println (:game-state @app))
                                       (recur)))))))]
    (reify
      om/IInitState
      (init-state [_]
        {:death-ch (chan)
         :evolving false})

      om/IDidMount
      (did-mount [_]
        (evolve-fn))

      om/IWillUnmount
      (will-unmount [_]
        (go (>! (om/get-state owner :death-ch) :death)))

      om/IRender
      (render [_]
        (dom/div nil
                 (dom/button #js {:onClick (fn [evt] (if (om/get-state owner :evolving)
                                                       (go (>! (om/get-state owner :death-ch) :death))
                                                       (evolve-fn)))}
                             "Start/Stop")
                 (om/build grid-component app))))))

(om/root
 app-component
 app-state
 {:target (. js/document (getElementById "app"))})
