(ns css100days.day3
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
                 :background "#272C34"
                 :color "#333"
                 :font-family "'Open Sans', Helvetica, sans-serif"
                 :-webkit-font-smoothing "antialiased"
                 :-moz-osx-font-smoothing "grayscale"}]
       [:.center {:position "absolute"
                  :top "50%"
                  :left "50%"
                  :transform "translate(-50%,-50%)"}]
       [:.circle {:position "relative"
                  :top "0"
                  :left "0"
                  :width "180px"
                  :height "180px"
                  :clip-path "circle(40%)"
                  :z-index 10}]
       [:.sky {:position "absolute"
               :background-color "#7DDFFC"
               :z-index 2
               :width "180px"
               :height "120px"}]
       [:.sun {:position "absolute"
               :background-color "#F0DE95"
               :z-index 5
               :top "60px"
               :left "20px"
               :width "30px"
               :height "30px"
               :border-radius "50%"}]
       [:.left-side {:position "absolute"
                     :clip-path "polygon(31% 0%, 0% 100%, 100% 100%)"
                     :z-index 8
                     :background "#F4F4F4"
                     :top "80px"
                     :left "55px"
                     :width "80px"
                     :height "40px"}]
       [:.right-side {:position "absolute"
                      :clip-path "polygon(0% 0%, 30% 100%, 100% 100%)"
                      :z-index 8
                      :background "#DDDADA"
                      :top "80px"
                      :left "80px"
                      :width "58px"
                      :height "40px"}]
       [:.shadow {:position "absolute"
                  :clip-path "polygon(0% 0%, 100% 0%, 30% 100%)"
                  :z-index 8
                  :background "rgba(0,0,0,0.2)"
                  :top "120px"
                  :left "55px"
                  :width "80px"
                  :height "20px"}]
       [:.ground {:position "absolute"
                  :background-color "#F0DE75"
                  :z-index 3
                  :top "120px"
                  :width "180px"
                  :height "80px"}]))