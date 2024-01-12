(ns css100days.day19
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))

(def x-gap 60)
(def left 107)
(def top 171)


(def moving-point
  [{:from {:top
           :left}
    :to {:top
         :left}}
   {:from {:top
           :left}
    :to {:top
         :left}}
   {:from {:top
           :left}
    :to {:top
         :left}}])

(defn move-keyframes
  [from to]
  (str "\n@keyframes move {"
       "\n\t0% {transform: " from "px}"
       "\n\t100% {transform: "to"px}"
       "\n}"))

(def outer-circle
  {:style [:.outer__circle
           {:position "absolute"
            :background "transparent"
            :width "53px"
            :height "53px"
            :border-width "2px"
            :border-style "solid"
            :border-color "red"
            :border-radius "50%"}]
   :element-fn (fn [top left]
                 [:div {:class "outer__circle"
                        :style {:top (str top "px")
                                :left (str left "px")}}])})

(def outer-circles 
  {:element (->> [[171 107.5] [171 180] [171 250]]
                 (map #(apply (:element-fn outer-circle) %))
                 (apply conj [:div]))})

(def inner-circle
  {:style [:.inner__circle 
           {:position "absolute"
            :top "175px"
            :left "111px"
            :background "black"
            :width "50px"
            :height "50px"
            :border-radius "50%"}]
   :element [:div {:class "inner__circle"}]})

(def frame
  {:style [:.frame {:position "absolute"
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
   :element [:div {:class "frame"}
             (:element inner-circle)
             (:element outer-circles)]} )


(defn styles
  []
  (->> [frame inner-circle outer-circle]
       (map :style)
       (apply css)))

(defn view
  []
  (fn []
    (:element frame)))

