(ns css100days.day9
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))

(comment 
  "Rainy Night: 
   The perfect time of day and the perfect weather to program in peace. 
   The raindrops even shatter on the ground.")



(def drop-comp
  [:div {:class "drop"}])

(def moon 
  [:div {:class "moon"}])

(def hill-background
  [:div {:class "hill-bg"}])

(def hill-2-background
  [:div {:class "hill-2-bg"}])

(def hill-3-background
  [:div {:class "hill-3-bg"}])


(def humidity
  [:div {:class "humidity"}
   "Humidity: 87%"])

(def wind
  [:div {:class "wind"}
   "Wind: E 7 km/h"])

(def temperature
  [:div {:class "temperature"}
   "12°"])

(def daily
  [:table {:class "daily"}
   [:tr
    [:td "TUE"]
    [:td "21° / 9°"]]
   [:tr
    [:td "WED"]
    [:td "23° / 10°"]]])

(def front
  [:div {:class "front"}
   temperature
   wind
   humidity
   daily])


(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      moon
      hill-background
      hill-2-background
      hill-3-background
      drop-comp]
     front]))

