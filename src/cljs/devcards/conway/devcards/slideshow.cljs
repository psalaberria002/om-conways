(ns conway.devcards.slideshow
  (:require-macros
    [devcards.core :as dc :refer [defcard defcard-om]])
  (:require [slides.slides :as slides]
            [conway.devcards.slides :as devslides]))

(defcard-om slideshow
            slides/slideshow-component
            devslides/slides-config
            {:opts {}}
            {})
