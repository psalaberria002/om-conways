(ns conway.devcards.still-lifes
  (:require-macros
   [devcards.core :as dc :refer [defcard defcard-om]])
  (:require [conway.core :refer [app-component grid-component]]))

(defcard-om block
            grid-component
            {:game-state #{[-1 -1] [0 -1]
                           [-1 0] [0 0]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}}}
            {})

(defcard-om block
            app-component
            {:game-state #{[-1 -1] [0 -1]
                           [-1 0] [0 0]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}}}
            {})

(defcard-om beehive
            grid-component
            {:game-state #{[-1 -1] [0 -1]
                           [-2 0] [1 0]
                           [-1 1] [0 1]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}}}
            {})

(defcard-om beehive
            app-component
            {:game-state #{[-1 -1] [0 -1]
                           [-2 0] [1 0]
                           [-1 1] [0 1]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}}}
            {})

(defcard-om loaf
            grid-component
            {:game-state #{[-1 -2] [0 -2]
                           [-2 -1] [1 -1]
                           [-1 0] [1 0]
                           [0 1]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}}}
            {})

(defcard-om loaf
            app-component
            {:game-state #{[-1 -2] [0 -2]
                           [-2 -1] [1 -1]
                           [-1 0] [1 0]
                           [0 1]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}}}
            {})

(defcard-om boat
            grid-component
            {:game-state #{[-1 -1] [0 -1]
                           [-1 0] [1 0]
                           [0 1]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}}}
            {})

(defcard-om boat
            app-component
            {:game-state #{[-1 -1] [0 -1]
                           [-1 0] [1 0]
                           [0 1]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}}}
            {})
