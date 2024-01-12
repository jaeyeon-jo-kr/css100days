(ns css100days.day2
  (:require [garden.core :refer [style]]
            [reagent.core :as r]))

(defonce positions
  (r/atom 
   {:line1 {:degree 0
            :top 0}
    :line2 {:degree 0
            :top 25
            :visibility "visible"}
    :line3 {:degree 0
            :top 50}}))

(defonce status (r/atom :ready))

(defn update-position
  []
  (case @status
    :three-bar->center
    (if (= (-> @positions :line1 :top)
           (-> @positions :line2 :top)
           (-> @positions :line3 :top))
      (reset! status :center->x) 
      (do
        (swap! positions #(update-in % [:line1 :top] inc))
        (swap! positions #(update-in % [:line3 :top] dec))))
    :ready nil
    :center->x 
    (if  (and (= (-> @positions :line1 :degree) 45)
              (= (-> @positions :line3 :degree) -45))
      (reset! status :ready)
      (do
        (swap! positions #(assoc-in % [:line2 :visibility] "hidden"))
        (swap! positions #(update-in % [:line1 :degree] inc))
        (swap! positions #(update-in % [:line3 :degree] dec))))
    
    
    nil))

(defn transform 
  [line-key]
  (str "rotate(" (-> @positions
                     line-key
                     :degree) "deg)"))

(defonce time-updater 
  (js/setInterval update-position 10))

(defn top
  [line-key]
  (-> @positions
      line-key
      :top))

(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "position"
             :on-click (fn [_] (reset! status :three-bar->center))}
       [:div {:class "line1"
              :style {:top (top :line1)
                      :transform (transform :line1)}}]
       [:div {:class "line2"
              :style {:top (top :line2)
                      :visibility (-> @positions :line2 :visibility)
                      :transform (transform :line2)}}]
       [:div {:class "line3"
              :style {:top (top :line3)
                      :transform (transform :line3)}}]]]]))



