(ns css100days.day29 
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
                 :background "#f1f3be"
                 :color "#333"
                 :font-family "'Open Sans', Helvetica, sans-serif"
                 :-webkit-font-smoothing "antialiased"
                 :-moz-osx-font-smoothing "grayscale"}]
       [:.center {:position "absolute"
                  :top "50%"
                  :left "50%"
                  :transform "translate(-50%,-50%)"}]
       [:.searchfield {:top "70%"
                       :left "50pt"}]
       [:.searchlist {:list-style "none"
                      :position "fixed"
                      :top "80%"
                      :left "-20%"}
        [:li {:cursor "pointer"}
         [:&:hover {:color "red"}]]]))