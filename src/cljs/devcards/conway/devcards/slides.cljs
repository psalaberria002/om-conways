(ns conway.devcards.slides
  (:require-macros
    [devcards.core :as dc :refer [defcard defcard-om]])
  (:require [slides.slides :as slides]
            [conway.core :refer [app-component]]))

(defonce slides-config
         (atom {:slides [{:main-slide? true}
                         {:title "me" :items ["Full Stack Software Engineer, working at Telenor Digital (Data Insights)"
                                              "Originally from the Basque Country, 5 years in Norway"
                                              "Working with Clojure + Clojurescript for the last 8 months (so much fun!)"
                                              "Daily tools: AWS, Terraform, Ansible, Kafka, Gerrit, and many more!"]}
                         {:title "OM" :items ["React Wrapper in cljs. Even faster than React! Immutable data structures"
                                              "Application state is stored in a global atom"
                                              "Components are functions from app state to DOM elements"
                                              "Cursors. Only render if necessary."
                                              "OM/React components lifecycle (IWillMount, IRender, IDidUpdate,...)"
                                              "Reusable components"]}
                         {:title "Conway's Game of Life"
                          :items ["Zero-player game, where its evolution is determined by its initial state, requiring no further input. Possible to create patterns."
                                  "A cell will die, or become alive, depending on the state of its neighbors in the previous generation."
                                  "Show me sOMe code! (conway.core)"]
                          :extra-component slides/slide1-extra
                          :extra {:state {:game-state #{[-1 -1] [0 -1] [1 -1]
                                                        [-2 0] [-1 0] [0 0]}
                                          :old-state #{}}
                                  :opts {:opts {:cell-size 20
                                                :grid {:x 4 :y 4}
                                                :speed-ms 1000}}}}
                         {:title "Figwheel" :items ["Live reloading (cljs + css)"
                                                    "How does it work?"
                                                    "Show Figwheel in action!"
                                                    "And now what, what if we want to visualize a component in different states at the same time? Devcards to the rescue!"]}
                         {:title "Devcards" :items ["Developed by the same guy as Figwheel"
                                                    "Useful for QA and testing"
                                                    "Also for developing components"
                                                    "Show devcards for conways namespaces!"
                                                    "Figwheel also works!"
                                                    "I guess you already realized, the slides are also inside a devcard ;)"]}
                         {:last-slide? true}
                         {:hiring-slide? true}]
                :selected-slide 0}))

(doseq [slide-obj (:slides @slides-config)]
  (defcard-om slide
              slides/slide-component
              slide-obj
              {}
              {}))