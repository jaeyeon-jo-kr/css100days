(ns css100days.day14
  (:require [garden.core :as g :refer [css]]
            [css100days.util :as u]
            [reagent.core :as r]
            [shadow.json :as json]))

(declare Card)
(declare BiCycle)
(declare Street)
(declare Front)
(declare Flip)
(declare Back)

(defprotocol Component
  (style [this])
  (element [this]))

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
      :top "20%"
      :left "10%"
      :width "80%"
      :height "50%"
      :background "yellow"}])
  (element [_this]
    [:div {:class "card"}
     (element (Flip.))]))

(defrecord CardHoverFlip []
  Component
  (style [_]
    [:.card:hover
     [:.flip 
      {:transform "rotateX(180deg) translate3d(0,0,0)"}]])
  (element [_]))

(defrecord Flip []
  Component
  (style [_]
    [:.flip 
     {:width "100%"
      :height "100%"
      :transform-style "preserve-3d"
      :perspective "1000px"
      :transition "all 1s ease-in-out"}])
  (element [_]
    [:div {:class "Flip"}
     (element (Front.))
     (element (Back.))]))

(defrecord CardHover []
 Component
  (style [_this]
    [:.card:hover
     {:transform "rotateX(180deg) translate3D(0,0,0)"
      :transition ""}])
  (element [_this]))

(defrecord CardHoverFront []
  Component
  (style [_this]
    [:.card:hover
     [:.front 
      {:visibility "hidden"}]])
  (element [_this]))

(defrecord CardHoverBack []
  Component
  (style [_this]
    [:.card:hover
     [:.back
      {:visibility "visible"}]])
  (element [_this]))

(defrecord BiCycle []
  Component
  (style [_this]
    [:.bicycle 
     {:position "absolute"
      :top "20%"
      :left "25%"
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
      :right "30%"
      :height "0.3rem"
      :width "4rem"
      :animation "street 1s linear 1s infinite"
      :background "black"}])
  (element [_]
    [:div {:class "street"}]))

(def street-keyframes
  (str "\n@keyframes street {"
       "\n\t0% {transform: translate3d(0, 0, 0)}"
       "\n\t100% {transform: translate3d(-100%, 0,0)}"
       "\n}"))

(defrecord Front [fields]
  Component
  (style [_]
    [:.front])
  (element [_]
    [:div {:class "front"}
     (element (BiCycle.))
     (element (Street.))]))

(defrecord Back []
  Component
  (style [_]
    [:.back
     {:visibility "hidden"}])
  (element [_]
    [:div {:class "back"}
     "Back"]))


(defn styles
  []
  (-> (g/css
       [:*
        {:box-sizing "border-box"}]
       (style (Framework.))
       (style (Back.))
       (style (Card.))
       (style (CardHover.))
       (style (CardHoverFront.))
       (style (CardHoverBack.))
       (style (CardHoverFlip.))
       (style (BiCycle.))
       (style (Street.)))
      (str bicycle-keyframes street-keyframes)))

(defn framework
  []
  (element (Framework.)))

(comment
  (type Framework)
  (satisfies? Framework Component)
  (implements? type 111)
  (meta #'Framework)
  (clojure.core/satisfies? Component Framework)
  (type framework)

  )

