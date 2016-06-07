(ns conway.devcards.slides
  (:require-macros
    [devcards.core :as dc :refer [defcard defcard-om]])
  (:require [slides.slides :as slides]
            [conway.core :refer [app-component]]))

(defonce slides-config
         (atom {:slides [{:main-slide? true}
                         {:title "me" :items ["Software Engineer at Telenor Digital (Data Insights)"
                                              "Originally from the Basque Country, 5 years in Norway"
                                              "Working with Clojure + Clojurescript for the last 8 months (so much fun!)"
                                              "Daily tools: AWS, Terraform, Ansible, Kafka, Gerrit, and many more!"]}
                         {:title "Clojurescript" :items ["\"Clojure\" for the browser"
                                                         "Immutable data structures"
                                                         "Functional"
                                                         "Namespaces"
                                                         "Great community (e.g. Clojurians slack channel)"
                                                         "React Wrappers: OM, Reagent, Quiescent,..."]}
                         {:title "OM" :items ["First React Wrapper in cljs. Even faster than React! Reference equality check"
                                              "Application state is stored in a global atom"
                                              "Components are functions from app state to DOM elements"
                                              "Cursors. Only render if necessary."
                                              "OM/React components lifecycle protocols (IWillMount, IRender, IDidUpdate,...)"
                                              "Components are reusable"]}
                         {:title "Figwheel" :items ["Live reloading (cljs + css)"
                                                    "How does it work?"
                                                    "Show Figwheel in action!"]}
                         {:title "Conway's Game of Life"
                          :items []
                          :extra-component slides/slide1-extra
                          :extra {:state {:game-state #{[-1 0] [-1 1]
                                                        [0 -1] [0 0] [1 0]}
                                          :old-state #{}}
                                  :opts {:opts {:cell-size 10
                                                :grid {:x 40 :y 40}
                                                :speed-ms 500}}}}
                         {:title "Devcards" :items ["Developed by the same guy as Figwheel"
                                                    "Useful for QA and testing"
                                                    "Also for developing components"
                                                    "Show devcards for conways namespaces!"
                                                    "Figwheel also works!"]}
                         {:last-slide? true}
                         {:hiring-slide? true}]
                :selected-slide 0}))

(doseq [slide-obj (:slides @slides-config)]
  (defcard-om slide
              slides/slide-component
              slide-obj
              {}
              {}))