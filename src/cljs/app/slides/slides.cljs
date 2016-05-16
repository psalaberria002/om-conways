(ns slides.slides
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [timeout <! >! alts! chan]]
            [keybind.core :as key]
            [conway.core :as conway])
  (:require-macros [cljs.core.async.macros :refer [go go-loop]]))

(defn main-slide
  [data]
  (reify
    om/IRender
    (render [_]
      (dom/div nil
               (dom/h2 #js {:style #js {:margin 0
                                        :padding 40
                                        :font-size "60px"
                                        :text-align "center"}}
                       "Interactive Development"
                       (dom/br nil)
                       "in Clojurescript"
                       (dom/br nil)
                       (dom/span #js {:style #js {:font-size "30px"}}
                                 "(" (dom/a #js {:href "https://github.com/omcljs/om"
                                                 :target "_blank"}
                                            "OM")
                                 "+"
                                 (dom/a #js {:href "https://github.com/bhauman/lein-figwheel"
                                             :target "_blank"}
                                        "Figwheel")
                                 "+"
                                 (dom/a #js {:href "https://github.com/bhauman/devcards"
                                             :target "_blank"}
                                        "DevCards") ")"))
               (dom/div #js {:style #js {:font-size "20px"
                                         :float "right"
                                         :margin-top "80px"
                                         :margin-right "100px"}}
                        (dom/div #js {:style #js {:width "100%"}}
                          "Paul Salaberria")
                        (dom/a #js {:href "https://github.com/psalaberria002"
                                    :target "_blank"}
                                 (dom/span #js {:style #js {:float "left"}}
                                           "psalaberria002")
                                 (dom/img #js {:style #js {:padding-left "5px"}
                                               :height "25px"
                                               :src "https://cdn4.iconfinder.com/data/icons/iconsimple-logotypes/512/github-512.png"})))))))

(defn last-slide
  [data]
  (reify
    om/IRender
    (render [_]
      (dom/div nil "QUESTIONS?")))
  )

(defn slide1-extra
  [data]
  (reify
    om/IRender
    (render [_]
      (let [extra (:extra data)]
        (om/build conway/app-component (:state extra) (:opts extra))))))

(defn slide-component
  [data]
  (reify
    om/IRender
    (render [_]
      (dom/div #js {:className "slide"
                    :style #js {:height "550px"}}
               (cond (:main-slide? data)
                     (om/build main-slide data)

                     (:last-slide? data)
                     (om/build last-slide data)

                     :else
                     (dom/div nil
                              (when-let [title (:title data)]
                                (dom/h4 nil title))
                              (when-let [items (:items data)]
                                (dom/ul #js {:className "slide-item-list"}
                                        (for [item items]
                                          (dom/li #js {:className "slide-list-item"}
                                                  item))))
                              (when-let [component (:extra-component data)]
                                (om/build component data))))))))

(defn slideshow-component
  [data]
  (reify
    om/IWillMount
    (will-mount [_]
      (key/bind! "right" ::next-slide #(om/transact! data :selected-slide
                                                     (fn [current-idx]
                                                       (if (= current-idx (- (count (:slides @data)) 1))
                                                         current-idx
                                                         (inc current-idx)))))
      (key/bind! "left" ::previous-slide #(om/transact! data :selected-slide
                                                        (fn [current-idx]
                                                          (if (= current-idx 0)
                                                            current-idx
                                                            (dec current-idx))))))

    om/IWillUnmount
    (will-unmount [_]
      (key/unbind! "right" ::next-slide)
      (key/unbind! "left" ::previous-slide))

    om/IRender
    (render [_]
      (let [slides (:slides data)
            selected-slide-idx (:selected-slide data)
            selected-slide (nth slides selected-slide-idx)]
        (dom/div nil
                 (om/build slide-component selected-slide))))))

