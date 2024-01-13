(ns css100days.day12
  (:require [garden.core :refer [css]]))

(def frame
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
            :background "#4CB6DE"
            :color "#333"
            :font-family "'Open Sans', Helvetica, sans-serif"
            :-webkit-font-smoothing "antialiased"
            :-moz-osx-font-smoothing "grayscale"}])

(def center
  [:.center {:position "absolute"
             :top "50%"
             :left "50%"
             :transform "translate(-50%,-50%)"}])

(def quote
  [:.quote {:position "absolute"
            :z-index 0
            :top "-340px"
            :left "-160px"
            :color "#6AC2E3"
            :font-size "240px"
            :font-family "Arial"}])

(def phrase
  [:.phrase {:position "absolute"
             :top "-140px"
             :left "-140px"
             :font-size "24px"
             :width "300px"
             :z-index 1
             :color "white"}])

(def writer
  [:.writer {:position "absolute"
             :top "100px"
             :left "-140px"
             :color "white"
             :font-size "24px"
             :width "100px"
             :font-style "italic"}])


(def tooltip
  [:.tooltip
   {:background "#286F8A"
    :cursor "pointer"
    :font-weight "bold"}
   ["&:hover"  "&:focus"
    [:.info {:visibility "visible"}]]
   [:.info
    {:box-sizing "border-box"
     :position "absolute"
     :bottom "130px"
     :left "0px"
     :width "300px"
     :height "110px"
     :line-height "24px"
     :border-radius "1px"
     :border-color "black"
     :background "black"
     :visibility "hidden"}
    ["&:before"]
    ["&:after"]
    [:.title
     {:background "white"
      :color "blue"}
     [:.word-pron
      {:display "block"
       :color "blue"}]
     [:.sound-button]]
    [:.content
     {:display "inline-block"
      :font-weight "initial"
      :font-size "18px"
      :padding "5px 6px"}]]])


(defn styles
  []
  (->> [frame center quote phrase writer tooltip]
       (apply css)))