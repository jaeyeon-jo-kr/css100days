(ns css100days.focus 
  (:require [garden.core :refer [css]]))

(defn styles
  []
  (css [:.framework {:position "absolute"
                     :top "50%"
                     :left "50%"
                     :width "400pt"
                     :height "400pt"}]
       [:.button1 
        {:background-color "#EA4C89"
         :border-radius "8px"
         :border-style "none"
         :box-sizing "border-box"
         :color "#FFFFFF"
         :cursor "pointer"}
        [:&:hover
         :&:focus
         {:background-color "#F082AC"}]]
       
       [:.button2 
        {:background-color "green"
         :color "white"}]
       [:.button3
        {:background-color "yellow"
         :color "black"}]
       [:.box 
        {:border "solid red"
         }]))  

