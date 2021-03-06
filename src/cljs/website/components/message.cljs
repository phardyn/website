(ns website.components.message
  (:require [reagent.core :as r]))

(defn message [title text theme closable]
  (let [message-hidden (r/atom false)]
    (fn [title text theme]
      [:div.message
       {:class (str "message--" theme
                    (when @message-hidden " message--hidden")
                    (when closable " message--closable"))}
       [:h3.message__title title]
       [:div.message__content text]
       (when closable
         [:button.message__close-btn {:on-click #(reset! message-hidden true)}
          [:i.fa.fa-times {:aria-hidden "true"}]])])))
