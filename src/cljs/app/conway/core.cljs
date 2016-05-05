(ns conway.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [timeout <! >! alts! chan]])
  (:require-macros [cljs.core.async.macros :refer [go go-loop]]))


(enable-console-print!)

;; Initial state, the "Blinker" lifeform
(defonce app-state (atom {:game-state #{[-1 0] [-1 1]
                                        [0 -1] [0 0] [1 0]}
                          :old-state #{}}))


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
  [app owner {:keys [cell-size grid]
              :or {cell-size 5
                   grid {:x 100
                         :y 100}}}]
  (reify
    om/IRender
    (render [_]
      (let [width (* cell-size (:x grid))
            height (* cell-size (:y grid))]
        (dom/div nil
          (dom/svg #js {:width width
                        :height height
                        :viewBox (str "-" (/ width 2) " -" (/ height 2) " " width " " height)}
                  (for [[x y] (:game-state app)]
                    (dom/rect #js {:width cell-size
                                   :height cell-size
                                   :fill (if ((:old-state app) [x y])
                                           "red"
                                           "green")
                                   :x (* x cell-size)
                                   :y (* y cell-size)}))))))))

(defn app-component
  [app owner {:keys [cell-size grid speed-ms]
              :or {cell-size 5
                   grid {:x 100
                         :y 100}
                   speed-ms 100}}]
  (let [evolve-fn (fn []
                    (om/set-state! owner :evolving true)
                    (let [command-ch (om/get-state owner :death-ch)]
                      (go-loop []
                               (let [[value from-ch] (alts! [command-ch (timeout speed-ms)])]
                                 (if (or (and (= from-ch command-ch)
                                              (= value :death))
                                         (= (:old-state @app) (:game-state @app)))
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
                 (om/build grid-component app {:opts {:cell-size cell-size
                                                      :grid grid}}))))))

(om/root
 app-component
 app-state
 {:target (. js/document (getElementById "app"))})
