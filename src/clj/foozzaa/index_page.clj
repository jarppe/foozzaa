(ns foozzaa.index-page
  (:require [hiccup.core :refer [html]]
            [hiccup.page :refer [html5 include-css include-js]]
            [garden.core :refer [css]]
            [foozzaa.env :as env]))

(def index-page
  (html
    (html5
      [:head
       [:title "foozzaa"]
       [:meta {:charset "utf-8"}]
       [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
       [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
       [:link {:href "foozzaa.css" :rel "stylesheet" :type "text/css"}]]
      [:body
       [:div#app
        [:h1 "Loading, please wait..."]]]
      (if env/prod?
        (include-js "foozzaa.js")
        (concat (include-js "out/goog/base.js" "react.js" "foozzaa.js")
                [[:script {:type "text/javascript"} "goog.require('foozzaa.ui.main');"]])))))
