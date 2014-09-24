(ns user)

(defn init []
  (require '[foozzaa.server :as server]
           '[foozzaa.env :as env]))

(defn run []
  (println "Loading foozzaa...")
  (require 'foozzaa.server)
  (println "Starting foozzaa...")
  ((resolve 'foozzaa.server/start-server) {"http" "8080", "nrepl" "6000"}))

"Server ready"
