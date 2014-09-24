(ns foozzaa.ui.root
  (:require [clojure.string :as s]
            [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [sablono.core :as html :refer-macros [html]]
            [foozzaa.info :as info]
            [foozzaa.ui.app-state :refer [app-state]]
            [foozzaa.ui.view.navbar :refer [navbar]]
            [foozzaa.ui.view.foozzaa :refer [foozzaa]]))

(defcomponent dialog [app owner]
  (init-state [_]
    {:open?    false
     :opened?  false
     :open     (fn [_]
                 (om/update-state! owner (fn [s] (assoc s :open? true :opened? false)))
                 (js/setTimeout (fn [] (om/set-state! owner :opened? true)) 1))
     :close    (fn [_]
                 (om/set-state! owner :opened? false)
                 (js/setTimeout (fn [] (om/set-state! owner :open? false)) 350))})
  (render-state [_ {:keys [open? opened? open close]}]
    (html
      [:div
       [:div.container {:style {:margin-top "36px"}}
        [:button.btn.btn-warning {:on-click open} "Paina mua!"]]
       (when open?
         [:div.dialog-overlay {:class (if opened? "opened") :on-click close}
          [:div.dialog-modal {:class (if opened? "opened")}
           [:div.modal-content
            [:div.modal-header
             [:button.close {:on-click close :type "button"}
              [:span "\u00D7"]
              [:span.sr-only "Close"]]
             [:h4.modal-title "Jiihaa"]]
            [:div.modal-body
             [:p "Lorem Ipsum is simply dummy text of the printing and typesetting industry."]
             [:p "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."]
             [:p "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."]]
            [:div.modal-footer
             [:button.btn.btn-default {:on-click close :type "button"} "Closez"]
             [:button.btn.btn-primary {:on-click close :type "button"} "Savez"]]]]])])))

(defcomponent connection-fail [app owner]
  (init-state [_]
    {:open?    false
     :opened?  false
     :open     (fn [_]
                 (om/update-state! owner (fn [s] (assoc s :open? true :opened? false)))
                 (js/setTimeout (fn [] (om/set-state! owner :opened? true)) 1))
     :close    (fn [_]
                 (om/set-state! owner :opened? false)
                 (js/setTimeout (fn [] (om/set-state! owner :open? false)) 350))})
  (render-state [_ {:keys [open? opened? open close]}]
    (html
      [:div
       [:div.container {:style {:margin-top "36px"}}
        [:button.btn.btn-warning {:on-click open} "Connexions gones"]]
       (when open?
         [:div.dialog-overlay.dark {:class (if opened? "opened") :on-click close}
          [:div.dialog-message
           [:h1 "Oh noes"]
           [:p "Connexions are lost, wait..."]
           [:p "...or click somewhere..."]]])])))

(defcomponent render-root [app owner]
  (render [_]
    (html
      [:div
       (om/build navbar app)
       (om/build foozzaa app)
       (om/build dialog app)
       (om/build connection-fail app)])))

(defn start []
  (om/root render-root app-state {:target (js/document.getElementById "app")}))
