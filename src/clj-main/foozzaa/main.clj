(ns foozzaa.main
  (:gen-class))

(defn -main [& args]
  (println "Loading foozzaa...")
  (require 'foozzaa.server)
  (println "Starting foozzaa...")
  ((resolve 'foozzaa.server/start-server) (apply hash-map args)))
