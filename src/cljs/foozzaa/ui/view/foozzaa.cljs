(ns foozzaa.ui.view.foozzaa
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [sablono.core :as html :refer-macros [html]]))

(defn handle-open! [owner]
  (fn [_] (om/set-state! owner :open? true)))

(defn handle-close! [owner]
  (fn [_] (om/set-state! owner :open? false)))

(defcomponent foozzaa [app owner]
  (init-state [_]
    {:open? false})
  (render-state [_ {:keys [open?]}]
    (html
      [:div.container
       [:h4 "Confirmation without dialog:"]
       [:div.sliding
        [:div.sliding-content {:class (if open? "open")}
         [:button.btn.btn-primary.sliding-element
          {:on-click (handle-open! owner)}
          "Foozzaaaa!!!"]
         [:button.btn.btn-danger.sliding-element
          {:on-click (handle-close! owner)}
          "For reals?"]
         [:button.btn.btn-default.sliding-element
          {:on-click (handle-close! owner)}
          "Nah"]]]])))
