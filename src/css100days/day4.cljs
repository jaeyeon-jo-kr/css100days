(ns css100days.day4
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))

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

(def style-status
  (r/atom
   [[:.frame {:position "absolute"
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
                :box-shadow "10px 10px 15px 0 rgba(0, 0, 0, 0.3)"}]]))

(defn find-index
  [key]
  (->> @style-status
       (keep-indexed (fn [i [style-key]]
                       (when (= style-key key)
                         i)))
       first))

(def attr-index 1)

(def status (r/atom
             {:circle1-rate 0
              :circle2-rate 0
              :circle3-rate 0
              :phase :circle1-increase}))

(defn circle1-decrease
  []
  (swap! status update :circle1-rate dec)
  (let [circle1-rate (:circle1-rate @status)]
    (swap! style-status assoc-in [(find-index :.circle1) attr-index :transform]
           (str "scale(" (/ circle1-rate 100) ")"))
    (when (<= circle1-rate 0)
      (swap! status assoc :phase :circle1-increase))))


(defn circle2-decrease
  []
  (swap! status update :circle2-rate dec)
  (let [circle2-rate (:circle2-rate @status)]
    (swap! style-status assoc-in [(find-index :.circle2) attr-index :transform]
           (str "scale(" (/ circle2-rate 100) ")"))
    (when (<= circle2-rate 0)
      (swap! status assoc :phase :circle1-decrease))))


(defn circle3-decrease
  []
  (swap! status update :circle3-rate dec)
  (let [circle3-rate (:circle3-rate @status)]
    (swap! style-status assoc-in [(find-index :.circle3) attr-index :transform]
           (str "scale(" (/ circle3-rate 100) ")"))
    (when (<= circle3-rate 0)
      (swap! status assoc :phase :circle2-decrease))))

(defn circle3-increase
  []
  (swap! status update :circle3-rate inc)
  (let [circle3-rate (:circle3-rate @status)]
    (swap! style-status assoc-in [(find-index :.circle3) attr-index :transform]
           (str "scale(" (/ circle3-rate 100) ")"))
    (when (>= circle3-rate 100)
      (swap! status assoc :phase :circle3-decrease))))

(defn circle2-increase
  []
  (swap! status update :circle2-rate inc)
  (let [circle2-rate (:circle2-rate @status)]
    (swap! style-status assoc-in [(find-index :.circle2) attr-index :transform]
           (str "scale(" (/ circle2-rate 100) ")"))
    (when (>= circle2-rate 100)
      (swap! status assoc :phase :circle3-increase))))

(defn circle1-increase
  []
  (swap! status update :circle1-rate inc)
  (let [circle1-rate (:circle1-rate @status)]
    (swap! style-status assoc-in [(find-index :.circle1) attr-index :transform]
           (str "scale(" (/ circle1-rate 100) ")"))
    (when (>= circle1-rate 100)
      (swap! status assoc :phase :circle2-increase))))

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


(defn styles
  []
  (apply css @style-status))

(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "circle1"}]
      [:div {:class "circle2"}]
      [:div {:class "circle3"}]]]))


