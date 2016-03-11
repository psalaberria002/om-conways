(ns conway.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [conway.core-test]))

(enable-console-print!)

(doo-tests 'conway.core-test)
