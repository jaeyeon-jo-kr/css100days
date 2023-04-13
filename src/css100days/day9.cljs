(ns css100days.day9
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))

(comment 
  "Rainy Night: 
   The perfect time of day and the perfect weather to program in peace. 
   The raindrops even shatter on the ground.")

(def moon-style
  [:.moon {:position "absolute"
           :top "-120px"
           :left "-120px"
           :width "67px"
           :height "67px"
           :border-radius "50px"
           :background "#F6EDBD"
           :overflow "hidden"
           :box-shadow "0 0 10px 0 #F6EDBD"}])

(def frame-style
  [:.frame {:position "absolute"
            :top "50%"
            :left "50%"
            :width "400px"
            :height "400px"
            :margin-top "-200px"
            :margin-left "-200px"
            :border-radius "2px"
            :box-shadow "4px 8px 16px 0 rgba(0,0,0,0.1)"
            :overflow "hidden"
            :background "#011628"
            :color "#333"
            :font-family "'Open Sans', Helvetica, sans-serif"
            :-webkit-font-smoothing "antialiased"
            :-moz-osx-font-smoothing "grayscale"}])

(def center-style
  [:.center {:position "absolute"
             :top "50%"
             :left "50%"
             :transform "translate(-50%,-50%)"}])

(def hill-style
  [:.hill-bg 
   {:position "absolute"
    :z-index "2"
    :width "337px"
    :height "281px"
    :top "40px"
    :left "-120px"
    :background "#26314F"
    :border-radius "40%"}])

(def hill-2-style
  [:.hill-2-bg
   {:position "absolute"
    :z-index "1"
    :width "337px"
    :height "281px"
    :top "20px"
    :left "-210px"
    :background "#24799e"
    :border-radius "60%"}])

(def hill-3-style
  [:.hill-3-bg
   {:position "absolute"
    :z-index "3"
    :width "337px"
    :height "281px"
    :top "50px"
    :left "-310px"
    :background "#028192"
    :border-radius "90%"}])

(def drop
  [:.drop 
   {:position }])


(def moon 
  [:div {:class "moon"}])

(def hill-background
  [:div {:class "hill-bg"}])

(def hill-2-background
  [:div {:class "hill-2-bg"}])

(def hill-3-background
  [:div {:class "hill-3-bg"}])

(defn styles
  []
  (css frame-style 
       center-style 
       moon-style 
       hill-style 
       hill-2-style
       hill-3-style))

(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      moon
      hill-background
      hill-2-background
      hill-3-background]]))

