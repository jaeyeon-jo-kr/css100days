(ns css100days.day29
  (:require [reagent.core :as r]))

(def search-word (r/atom false))

(defn data-list []
  (let [search-items 
        (if @search-word 
          [(str "abc : " @search-word " at all ")
           (str "cde : " @search-word " at all ")]
          '())]
    [:ul {:class "searchlist"}
     (for [item search-items]
       [:li item])]))

(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "searching"}
       [:input {:type "text" :class "searchfield" 
                :id "searchbox"
                :place-holder "Input search ..."
                :list "search-suggest"
                :on-change 
                (fn [e]
                  (reset! search-word (-> e .-target .-value)))}]
       (data-list)]]]))