(ns css100days.day4
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))


(def status (r/atom
             {:circle1-rate 0
              :circle2-rate 0
              :circle3-rate 0
              :phase :circle1-increase}))

(defn circle1-decrease
  []
  (swap! status update :circle1-rate dec)
  (when (<= (:circle1-rate @status) 0)
    (swap! status assoc :phase :circle1-increase)))


(defn circle2-decrease
  []
  (swap! status update :circle2-rate dec)
  (when (<= (:circle2-rate @status) 0)
    (swap! status assoc :phase :circle1-decrease)))


(defn circle3-decrease
  []
  (swap! status update :circle3-rate dec)
  (when (<= (:circle3-rate @status) 0)
    (swap! status assoc :phase :circle2-decrease)))

(defn circle3-increase
  []
  (swap! status update :circle3-rate inc)
  (when (>= (:circle3-rate @status) 100)
    (swap! status assoc :phase :circle3-decrease)))

(defn circle2-increase
  []
  (swap! status update :circle2-rate inc)
  (when (>= (:circle2-rate @status) 100)
    (swap! status assoc :phase :circle3-increase)))

(defn circle1-increase
  []
  (swap! status update :circle1-rate inc)
  (when (>= (:circle1-rate @status) 100)
    (swap! status assoc :phase :circle2-increase)))

(defn update-animation
  []
  (case (:phase @status)
    :circle1-increase (circle1-increase)
    :circle2-increase (circle2-increase)
    :circle3-increase (circle3-increase)
    :circle3-decrease (circle3-decrease)
    :circle2-decrease (circle2-decrease)
    :circle1-decrease (circle1-decrease)))

(def interval 10)

(defonce time-updater
  (js/setInterval update-animation interval))

(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "circle1"
             :style {:transform (str "scale(" (/ (:circle1-rate @status) 100) ")")}}]
      [:div {:class "circle2"
             :style {:transform (str "scale(" (/ (:circle2-rate @status) 100) ")")}}]
      [:div {:class "circle3"
             :style {:transform (str "scale(" (/ (:circle3-rate @status) 100) ")")}}]]]))


