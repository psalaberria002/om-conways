(ns conway.devcards.slides
  (:require-macros
    [devcards.core :as dc :refer [defcard defcard-om]])
  (:require [slides.slides :as slides]
            [conway.core :refer [app-component]]))

(defonce slides-config (atom {:slides [{:main-slide? true}
                                       {:title "OM" :items ["React Wrapper"
                                                            "Very fast due to immutable objects in cljs"
                                                            "Application state is stored in a global atom"
                                                            "Components are functions from parts of the state (Cursors) to DOM elements"
                                                            "Show code! (conway.core)"]
                                        :extra-component slides/slide1-extra
                                        :extra {:state {:game-state #{[-1 -1] [0 -1] [1 -1]
                                                                      [-2 0] [-1 0] [0 0]}
                                                        :old-state #{}}
                                                :opts {:opts {:cell-size 20
                                                              :grid {:x 4 :y 4}
                                                              :speed-ms 500}}}}
                                       {:title "Figwheel" :items ["Live reloading (cljs + css)"
                                                                  "How does it work?"
                                                                  "Show Figwheel in action!"
                                                                  "And now what, what if we want to visualize a component in different states at the same time? Devcards to the rescue."]}
                                       {:title "Devcards" :items ["See components in different states at the same time."
                                                                  "Useful for QA and testing"
                                                                  "Also for developing components"
                                                                  "Show devcards for conways namespaces!"
                                                                  "Figwheel also works!"
                                                                  "I guess you already realized, the slides are also inside devcard ;)"]}
                                       {:last-slide? true}]
                              :selected-slide 0}))

(doseq [slide-obj (:slides @slides-config)]
  (defcard-om slide
              slides/slide-component
              slide-obj
              {}
              {}))