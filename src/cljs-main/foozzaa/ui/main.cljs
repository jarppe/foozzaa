(ns foozzaa.ui.main
  (:require [foozzaa.ui.root :as root]
            [foozzaa.ui.error :refer [error]]))

(-> js/window .-onerror (set! (fn [message url line]
                                (error {:type     "on-error"
                                        :message  message
                                        :url      url
                                        :line     line})
                                false)))

(-> js/window .-onload (set! root/start))
