(ns css100days.day9
  (:require [garden.core :refer [css]]))

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
            :background "#1A2238"
            :color "#333"
            :font-family "'Open Sans', Helvetica, sans-serif"
            :-webkit-font-smoothing "antialiased"
            :-moz-osx-font-smoothing "grayscale"}])

(def center-style
  [:.center {:position "absolute"
             :top "50%"
             :left "50%"
             :transform "translate(-50%,-50%)"}])

(def drop-style
  [:.drop {:position "absolute"
           :left "18px"
           :bottom "-30px"
           :width "8px"
           :height "8px"
           :border-radius "50%"
           :background "white"
           :animation "drop 2s linear 0.4s infinite"
           :--webkit-animation "drop 0.2s linear 0.4s infinite"
           :transform-origin "50% 100%"
           :animation-fill-mode "both"}
   [:&:before
    {:position "absolute"
     :display "block"
     :background "#028192"
     :content ""
     :top "10px"
     :left "40px"
     :width "30px"
     :height "10px"}]
   [:&:after
    {:position "absolute"
     :background "blue"
     :border-radius "50%"
     :width "40px"
     :height "50px"}]])

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

(def temperature-style
  [:.temperature
   {:position "relative"
    :z-index 20
    :left "20px"
    :top "30px"
    :font-size "35px"}])

(def wind-style
  [:.wind
   {:position "relative"
    :top "-10px"
    :left "80px"}])

(def humidity-style
  [:.humidity
   {:position "relative"
    :top "-10px"
    :left "80px"}])

(def daily-style
  [:.daily
   {:position "relative"
    :top "-57%"
    :left "65%"}])

(def front-style
  [:.front
   {:position "absolute"
    :z-index 10
    :width "400px"
    :height "90px"
    :left "0px"
    :bottom "0px"
    :background "#fff"}
   temperature-style
   wind-style
   humidity-style
   daily-style])

(def drop-keyframe
  (str "\n@keyframes drop { 
  0% {
    transform: translate3d(40px, -400px, 0) scaleX(1) scaleY(1) rotate(17deg);
  }
  85% {
    transform: translate3d(0, 0, 0) scaleX(1) scaleY(1) rotate(17deg);
  }
  100% {
    transform: translate3d(0, 0, 0) scaleX(3) scaleY(0) rotate(0deg);
  }
}"))



(defn styles
  []
  (-> (->> [frame-style
            center-style
            moon-style
            hill-style
            hill-2-style
            hill-3-style
            front-style
            drop-style]
           (apply css))
      (str " " drop-keyframe)))