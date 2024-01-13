(ns css100days.day7
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))


;;Notifications, Search and Menu: 
;;The three cornerstones of any application?
;;Brought together in the smallest possible space.

(def search-active (r/atom false))

(def main-class (r/atom "main"))


(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "menu-panel"}
       [:div {:class "menu-item"}
        [:div {:class "menu-item-text"}
         "Dashboard"]]
       [:div {:class "menu-item"}
        [:div {:class "menu-item-text"}
         "Profile"]]
       [:div {:class "menu-item"}
        [:div {:class "menu-item-text"}
         "Notifications"]]
       [:div {:class "menu-item"}
        [:div {:class "menu-item-text"}
         "Messages"]]
       [:div {:class "menu-item"}
        [:div {:class "menu-item-text"}
         "Settings"]]]
      [:div {:class @main-class :id "main"}
       [:div {:class "bar"}
        [:input {:type "text" 
                 :class (if @search-active
                          "search active"
                          "search")}]
        [:img {:class "menu-btn"
               :src "asset/menu.svg"
               :alt "menu"
               :on-click (fn [_]
                           (swap! main-class {"main" "main show-menu"
                                              "main show-menu" "main"}))} 
         ]
        [:span {:class "text"} "Notifications"]
        [:img {:class "show-search-btn"
               :src "asset/magnify.svg"
               :alt "Search"
               :on-click (fn [_]
                           (swap! search-active not))}]]
       [:div {:class "body"}
        [:div {:class "horizontal-bar"}]
        [:ul {:class "list"}
         [:li {:class "item"}
          [:div {:class "circle"}]
          [:p {:class "time"}
           "9:24 AM"]
          [:p {:class "content"}
           "John Walker posted a photo on your wall."]]
         [:li {:class "item"}
          [:div {:class "circle"}]
          [:p {:class "time"}
           "8:19 AM"]
          [:p {:class "content"}
           "Alice Parker commented your last post."]]
         [:li {:class "item"}
          [:div {:class "circle"}]
          [:p {:class "time"}
           "Yesterday"]
          [:p {:class "content"}
           "Luke Wayne added you as friend."]]]]]]]))

