(ns conway.devcards.oscillators
  (:require-macros
   [devcards.core :as dc :refer [defcard defcard-om]])
  (:require [conway.core :refer [app-component population-view]]))

(defcard-om blinker
            "*Blinker (period 2)*"
            app-component
            {:game-state #{[0 -1] [0 0] [0 1]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}
                    :speed-ms 500}}
            {:inspect-data true})

(defcard-om toad
            "*Toad (period 2)*"
            app-component
            {:game-state #{[-1 -1] [0 -1] [1 -1]
                           [-2 0] [-1 0] [0 0]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}
                    :speed-ms 500}}
            {})

(defcard-om beacon
            "*Beacon (period 2)*"
            app-component
            {:game-state #{[-2 -2] [-1 -2]
                           [-2 -1] [-1 -1]
                           [0 0] [1 0]
                           [0 1] [1 1]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}
                    :speed-ms 500}}
            {})

(defcard-om pulsar
            "*Pulsar (period 3)*"
            app-component
            {:game-state #{[-4 -6] [-3 -6] [-2 -6] [2 -6] [3 -6] [4 -6]
                           [-6 -4] [-1 -4] [1 -4] [6 -4]
                           [-6 -3] [-1 -3] [1 -3] [6 -3]
                           [-6 -2] [-1 -2] [1 -2] [6 -2]
                           [-4 -1] [-3 -1] [-2 -1] [2 -1] [3 -1] [4 -1]
                           [-4 1] [-3 1] [-2 1] [2 1] [3 1] [4 1]
                           [-6 2] [-1 2] [1 2] [6 2]
                           [-6 3] [-1 3] [1 3] [6 3]
                           [-6 4] [-1 4] [1 4] [6 4]
                           [-4 6] [-3 6] [-2 6] [2 6] [3 6] [4 6]}
             :old-state #{}}
            {:opts {:cell-size 10
                    :grid {:x 15 :y 15}
                    :speed-ms 500}}
            {})

(defcard-om pentadecathlon
            "*Pentadecathlon (period 15)*"
            app-component
            {:game-state #{[0 -4]
                           [0 -3]
                           [-1 -2][1 -2]
                           [0 -1]
                           [0 0]
                           [0 1]
                           [0 2]
                           [-1 3][1 3]
                           [0 4]
                           [0 5]}
             :old-state #{}}
            {:opts {:cell-size 10
                    :grid {:x 15 :y 15}
                    :speed-ms 500}}
            {})
