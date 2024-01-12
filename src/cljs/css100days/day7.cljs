(ns css100days.day7
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))


;;Notifications, Search and Menu: 
;;The three cornerstones of any application?
;;Brought together in the smallest possible space.

(def search-active (r/atom false))

(def bar-menu-button-style
  [:.menu-btn
   {:position "absolute"
    :top "23%"
    :left "5%"
    :width "15%"
    :filter "invert(46%)
             sepia(28%) 
             saturate(2425%)
             hue-rotate(158deg)
             brightness(98%)
             contrast(101%)"}
   ["&:hover" {:cursor "pointer"
               :filter "invert(95%) sepia(68%) saturate(1205%) hue-rotate(163deg) brightness(99%) contrast(103%)"}]])

(def bar-text-style
  [:.text
   {:position "absolute"
    :top "40%"
    :left "35%"
    :font-size "20px"
    :color "white"}])


(def bar-search-button-style
  [:.show-search-btn
   {:position "absolute"
    :top "25%"
    :left "80%"
    :width "15%"
    :filter "invert(46%)
             sepia(28%) 
             saturate(2425%)
             hue-rotate(158deg)
             brightness(98%)
             contrast(101%)"}
   ["&:hover" {:cursor "pointer"
               :filter "invert(95%) sepia(68%) saturate(1205%) hue-rotate(163deg) brightness(99%) contrast(103%)"}]])

(def bar-search-style
  [:.search 
   {:position "absolute"
    :top "30%"
    :left "5%"
    :z-index 10
    :visibility "hidden"
    :height "50%"
    :width "70%"}
   [:&.active {:visibility "visible"}]])


(def bar-style
  [:.bar {:position "relative"
           :background "blue"
           :top "0px"
           :left "0px"
           :height "80px"
           :width "320px"}
   bar-menu-button-style
   bar-search-style
   bar-search-button-style
   bar-text-style])

(def time-style
  [:.time {:display "block"}])

(def content-style
  [:.content {:position "relative"}])

(def horizontal-bar-style
  [:.horizontal-bar
   {:position "absolute"
    :top "0px"
    :left "16px"
    :background "pink"
    :width "18px"
    :height "250px"}])

(def circle-style
  [:.circle
   {"box-sizing" "border-box"
    "position" "absolute"
    "height" "11px"
    "width" "11px"
    "background" "#eddcc8"
    "border" "2px solid pink"
    "box-shadow" "0 0 0 3px"
    "border-radius" "6px"
    "top" "0"
    "left" "-20px"}])
			 

(def item-style
  [:.item {:position "relative"}
   ["&:hover" {:cursor "pointer"
               :color "#65a7dd"}]
   time-style
   content-style])


(def list-style
  [:.list {:position "absolute"
           :color "white"
           :top "-5%"}
   item-style])

(def body-style
  [:.body {:position "relative"
           :background "purple"
           :top "0px"
           :left "0px"
           :height "250px"
           :width "320px"}
   horizontal-bar-style
   list-style])

(def notification-style
  [:.main {:transition "all 1s"}
   [:&.show-menu {:position "relative"
                  :transform "translateX(100px)"}]
   bar-style
   body-style
   circle-style])

(def main-class (r/atom "main"))

(def menu-item-text-style
  [:.menu-item-text
   {:position "absolute"
    :color "#2ee8bb"
    :left "6px"}])

(def menu-item-style
  [:.menu-item
   {:position "relative"
    :top "10px"
    :height "40px"
    :margin-top "5px"}
   ["&:hover" {:cursor "pointer"
               :background "#2ee8bb"}
    [:.menu-item-text {:color "#bf8"}]]
   menu-item-text-style])

(def menu-panel-style
  [:.menu-panel
   {:position "absolute"
    :top "10px"
    :bottom "0px"
    :width "200px"
    :height "250px"
    :background "#bf8"}
   menu-item-style])


(defn styles
  []
  (fn []
    (css [:.frame {:position "absolute"
                   :top "50%"
                   :left "50%"
                   :width "400px"
                   :height "400px"
                   :margin-top "-200px"
                   :margin-left "-200px"
                   :border-radius "2px"
                   :box-shadow "4px 8px 16px 0 rgba(0,0,0,0.1)"
                   :overflow "hidden"
                   :background "#fff"
                   :color "#333"
                   :font-family "'Open Sans', Helvetica, sans-serif"
                   :-webkit-font-smoothing "antialiased"
                   :-moz-osx-font-smoothing "grayscale"}]
         [:.center {:position "absolute"
                    :top "50%"
                    :left "50%"
                    :transform "translate(-50%,-50%)"}
          notification-style
          menu-panel-style])))

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

