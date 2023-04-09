(ns css100days.day7
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))


;;Notifications, Search and Menu: 
;;The three cornerstones of any application?
;;Brought together in the smallest possible space.

(def bar-menu-button-style
  [:.menu 
   {:position "absolute"
    :top "40%"
    :left "5%"
    :color "white"}
   ["&:hover" {:cursor "pointer"}]])

(def bar-text-style
  [:.text
   {:position "absolute"
    :top "40%"
    :left "40%"
    :color "white"}])


(def bar-search-button-style
  [:.search
   {:position "absolute"
    :top "40%"
    :left "85%"
    :color "white"}
   ["&:hover" {:cursor "pointer"}]])



(def bar-style
  [:.bar {:position "relative"
           :background "blue"
           :top "0px"
           :left "0px"
           :height "80px"
           :width "320px"}
   bar-menu-button-style
   bar-search-button-style
   bar-text-style])

(def clock-style
  [:.clock {}])

(def content-style
  [:.content {:position "relative"
              }])


(def item-style
  [:.item {:position "relative"}
   ["&:hover" {:cursor "pointer"
               :color "#65a7dd"}]
   clock-style
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
   list-style])



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
          bar-style
          body-style])))

(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "bar"}
       [:div {:class "menu"} "menu"]
       [:span {:class "text"} "Notifications"]
       [:div {:class "search"} "search"]]
      [:div {:class "body"}
       [:ul {:class "list"}
        [:li {:class "item"}
         [:p {:class "clock"}
          "9:24 AM"]
         [:p {:class "content"}
          "John Walker posted a photo on your wall."]]
        [:li {:class "item"}
         [:p {:class "clock"}
          "8:19 AM"]
         [:p {:class "content"} 
          "Alice Parker commented your last post."]]
        [:li {:class "item"}
         [:p {:class "clock"} 
          "Yesterday"]
         [:p {:class "content"}
          "Luke Wayne added you as friend."]]]]]]))

