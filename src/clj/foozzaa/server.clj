(ns foozzaa.server
  (:require [clojure.string :as s]
            [clojure.tools.nrepl.server :as nrepl]
            [org.httpkit.server :as http-kit]
            [foozzaa.env :as env]
            [foozzaa.routing :refer [routes]]))

(defn start-server [{:strs [http nrepl] :or {http "8080"}}]
  (let [http-port (Integer/parseInt http 10)
        nrepl-port (if nrepl (Integer/parseInt nrepl 10))]
    (if nrepl-port
      (nrepl/start-server :port nrepl-port))
    (http-kit/run-server (if env/prod? routes #'routes) {:port http-port})
    (println (format "Started foozzaa: mode=%s, http=%d, JVM=%s, clojure=%s, nrepl=%s"
                     (name env/mode)
                     http-port
                     (System/getProperty "java.version")
                     (s/join "." ((juxt :major :minor :incremental) *clojure-version*))
                     (or nrepl "-")))))
