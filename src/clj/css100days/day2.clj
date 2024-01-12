(ns css100days.day2
  (:require [garden.core :refer [css]]))


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
                 :background "#3FAF82"
                 :color "#fff"
                 :font-family "'Open Sans', Helvetica, sans-serif"
                 :-webkit-font-smoothing "antialiased"
                 :-moz-osx-font-smoothing "grayscale"}]
       [:.center {:position "absolute"
                  :top "50%"
                  :left "50%"
                  :transform "translate(-50%,-50%)"}]
       [:.position {:position "relative"
                    :height "100px"
                    :width "100px"}
        [:.line1 {:position "absolute"
                  :z-index 10
                  :left "0"
                  :background "#fff"
                  :border-radius "3px"
                  :height "8px"
                  :width "80px"
                  :border "24px #fff"}]
        [:.line2 {:position "absolute"
                  :z-index 11
                  :left "0"
                  :background "#fff"
                  :border-radius "3px"
                  :height "8px"
                  :width "80px"
                  :border "24px #fff"
                  :box-shadow "0 0 13px 0 rgba(0,0,0,0.2)"}]
        [:.line3 {:position "absolute"
                  :z-index 12
                  :left "0"
                  :background "#fff"
                  :border-radius "3px"
                  :height "8px"
                  :width "80px"
                  :border "24px #fff"
                  :box-shadow "0 0 13px 0 rgba(0,0,0,0.2)"}]]))