(ns user
  (:require [conway.server]
            [ring.middleware.reload :refer [wrap-reload]]
            [figwheel-sidecar.repl-api :as figwheel]
            [conway.server :as server]))

;; Let Clojure warn you when it needs to reflect on types, or when it does math
;; on unboxed numbers. In both cases you should add type annotations to prevent
;; degraded performance.
(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
(def http-handler
  (wrap-reload #'conway.server/http-handler))

(defn run []
  (figwheel/start-figwheel!))

(defn start-app []
  (server/-main))

(def browser-repl figwheel/cljs-repl)
