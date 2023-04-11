(ns css100days.day8
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))

(def sparkle-style
  [:.sparkle
   {:position "absolute"
    :top "50px"
    :left "50px"
    :width "50px"
    :height "50px"
    :background "#fff"
    :border-radius "50%"
    :filter "blur(1px)"
    :transform "rotate(100 deg)"
    }
   ["&:after" {:position "absolute"
               :display "block"
               :top "100px"
               :left "100px"
               :width "40px"
               :height "40px"
               :transform "rotate(150deg)"
               :transform-origin "20px 20px"
               :animation "rotate(10 deg) 0.5s ease-in-out 0.5s infinite"
               :background "#fff"
               :border-radius "1%"
               :filter "blur(1px)"}]])


(def ball-style
  [:.ball
   {:position "relative"
    :width "90px"
    :height "90px"
    :background "#fff"
    :border-radius "80%"
    :filter "blur(2px)"}
   ])



(defn styles
  []
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
                 :background "#000000"
                 :color "#000000"
                 :font-family "'Open Sans', Helvetica, sans-serif"
                 :-webkit-font-smoothing "antialiased"
                 :-moz-osx-font-smoothing "grayscale"}]
       [:.center {:position "absolute"
                  :top "50%"
                  :left "50%"
                  :transform "translate(-50%,-50%)"}]
       ball-style
       sparkle-style
       ["@keyframe" "rotate"
        [:from {:transform "rotate(0deg) translate3d(0, 0, 0)"}]
        [:to {:transform "rotate(360deg) translate3d(0, 0, 0)"}]]))




(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "ball"}]
      [:div {:class "sparkle"}]
      ]]))

