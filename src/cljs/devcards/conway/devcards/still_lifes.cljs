(ns conway.devcards.still-lifes
  (:require-macros
   [devcards.core :as dc :refer [defcard defcard-om]])
  (:require [conway.core :refer [app-component population-view]]))

(defcard-om block
            "*Block initital state*"
            population-view
            {:game-state #{[-1 -1] [0 -1]
                           [-1 0] [0 0]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}}}
            {})

(defcard-om block
            "*Block final state*"
            app-component
            {:game-state #{[-1 -1] [0 -1]
                           [-1 0] [0 0]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}}}
            {})

(defcard-om beehive
            "*Beehive initial state*"
            population-view
            {:game-state #{[-1 -1] [0 -1]
                           [-2 0] [1 0]
                           [-1 1] [0 1]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}}}
            {})

(defcard-om beehive
            "*Beehive final state*"
            app-component
            {:game-state #{[-1 -1] [0 -1]
                           [-2 0] [1 0]
                           [-1 1] [0 1]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}}}
            {})

(defcard-om loaf
            "*Loaf initial state*"
            population-view
            {:game-state #{[-1 -2] [0 -2]
                           [-2 -1] [1 -1]
                           [-1 0] [1 0]
                           [0 1]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}}}
            {})

(defcard-om loaf
            "*Loaf final state*"
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
            "*Boat initial state*"
            population-view
            {:game-state #{[-1 -1] [0 -1]
                           [-1 0] [1 0]
                           [0 1]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}}}
            {})

(defcard-om boat
            "*Boat final state*"
            app-component
            {:game-state #{[-1 -1] [0 -1]
                           [-1 0] [1 0]
                           [0 1]}
             :old-state #{}}
            {:opts {:cell-size 20
                    :grid {:x 4 :y 4}}}
            {})
