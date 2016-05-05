(ns conway.devcards.spaceships
  (:require-macros
   [devcards.core :as dc :refer [defcard defcard-om]])
  (:require [conway.core :refer [app-component grid-component]]))

(defcard-om spaceshift-factory
            "*Spaceship factory*"
            app-component
            {:game-state #{[1 5] [2 5] [1 6] [2 6]
                           [11 5] [11 6] [11 7] [12 4] [12 8] [13 3] [13 9] [14 3] [14 9]
                           [15 6] [16 4] [16 8] [17 5] [17 6] [17 7] [18 6]
                           [21 3] [21 4] [21 5] [22 3] [22 4] [22 5] [23 2] [23 6] [25 1] [25 2] [25 6] [25 7]
                           [35 3] [35 4] [36 3] [36 4]}
             :old-state #{}}
            {:opts {:cell-size 3
                    :grid {:x 100 :y 100}
                    :speed-ms 10}}
            {})

(defcard-om die-hard
            "*Die Hard* Dissappears after 130 generations"
            grid-component
            {:game-state #{[2 -1]
                           [-4 0] [-3 0]
                           [-3 1] [1 1] [2 1] [3 1]}
             :old-state #{}}
            {:opts {:cell-size 10
                    :grid {:x 10 :y 10}}}
            {})

(defcard-om die-hard
            "*Die Hard* Dissappears after 130 generations"
            app-component
            {:game-state #{[2 -1]
                           [-4 0] [-3 0]
                           [-3 1] [1 1] [2 1] [3 1]}
             :old-state #{}}
            {:opts {:cell-size 10
                    :grid {:x 50 :y 50}}}
            {})


