(ns css100days.day4
  (:require [garden.core :refer [css]]))

(def center-x 0)
(def center-y 0)

(defn top
  [radius]
  (-> (- center-y radius)
      (str "px")))

(defn left
  [radius]
  (-> (- center-x radius)
      (str "px")))

(defn width
  [radius]
  (-> (* 2 radius)
      (str "px")))

(defn height
  [radius]
  (-> (* 2 radius)
      (str "px")))

(defn styles []
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
                 :background "#E56262"
                 :color "#333"
                 :font-family "'Open Sans', Helvetica, sans-serif"
                 :-webkit-font-smoothing "antialiased"
                 :-moz-osx-font-smoothing "grayscale"}]
       [:.center {:position "absolute"
                  :top "50%"
                  :left "50%"
                  :transform "translate(-50%,-50%)"}]
       [:.circle1 {:position "absolute"
                   :z-index 8
                   :top (top 60)
                   :left (left 60)
                   :clip-path "circle(40%)"
                   :background-color "#FFFFFF"
                   :transform "scale(0)"
                   :border-radius "50%"
                   :width (width 60)
                   :height (height 60)
                   :box-shadow "0px 1px 2px 0 rgba(0, 0, 0, 0.3)"}]
       [:.circle2 {:position "absolute"
                   :z-index 9
                   :top (top 40)
                   :left (left 40)
                   :clip-path "circle(40%)"
                   :background-color "#F8F"
                   :transform "scale(0)"
                   :border-radius "50%"
                   :width (width 40)
                   :height (height 40)
                   :box-shadow "10px 10px 15px 0 rgba(0, 0, 0, 0.3)"}]
       [:.circle3 {:position "absolute"
                   :z-index 10
                   :top (top 20)
                   :left (left 20)
                   :clip-path "circle(40%)"
                   :border-radius "50%"
                   :background-color "#F6F7"
                   :transform "scale(0)"
                   :width (width 20)
                   :height (height 20)
                   :box-shadow "10px 10px 15px 0 rgba(0, 0, 0, 0.3)"}]))