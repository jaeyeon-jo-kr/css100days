(ns css100days.day14
  (:require [garden.core :as g :refer [css]]
            [css100days.util :as u]
            [reagent.core :as r]
            [shadow.json :as json]))

(defprotocol Component
  (style [this])
  (element [this]))
(declare Card)
(declare BiCycle)
(declare Street)

(defrecord Framework []
 Component
  (style [_this]
    [:.framework
     {:position "absolute"
      :top "30%"
      :left "30%"
      :width "50%"
      :height "50%"
      :border "solid"
      :border-radius "2px"
      :background "#222"
      :color "#333"}])
  (element [_this]
    [:div {:class "framework"}
     (element (Card.))]))

(defrecord Card []
 Component
  (style [_this]
    [:.card
     {:position "absolute"
      :top "30%"
      :left "10%"
      :width "80%"
      :height "40%"
      :background "yellow"}])
  (element [_this]
    [:div {:class "card"}
     (element (BiCycle.))
     (element (Street.))]))

(defrecord CardHover []
 Component
  (style [_this]
    [:.card:hover
     {:transform "rotateX(180deg) translate3D(0,0,0)"}])
  (element [_this]))

(defrecord BiCycle []
  Component
  (style [_this]
    [:.bicycle 
     {:position "absolute"
      :top "20%"
      :left "30%"
      :animation "bike .6s ease-in-out infinite"}])
  (element [_this]
    [:img {:class "bicycle"
           :src "https://100dayscss.com/codepen/bycicle.svg"}]))

(def bicycle-keyframes
  (str "\n@keyframes bike {"
       "\n\t0%, 100% {transform: ScaleY(1)}"
       "\n\t50% {transform: ScaleY(1.1)}"
       "\n}"))

(defrecord Street []
  Component
  (style [_]
    [:.street
     {:position "absolute"
      :bottom "20%"
      :right "20%"
      :height "0.3rem"
      :width "4rem"
      :animation "street 1s linear 1s infinite"
      :background "black"}])
  (element [_]
    [:div {:class "street"}]))

(def street-keyframes
  (str "\n@keyframes street {"
       "\n\t0% {transform: translate3d(0, 0, 0)}"
       "\n\t100% {transform: translate3d(-300%, 0,0)}"
       "\n}"))

(defn styles
  []
  (-> (g/css
       [:*
        {:box-sizing "border-box"}]
       (style (Framework.))
       (style (Card.))
       (style (CardHover.))
       (style (BiCycle.))
       (style (Street.)))
      (str bicycle-keyframes street-keyframes)))

(defn framework
  []
  (element (Framework.)))
