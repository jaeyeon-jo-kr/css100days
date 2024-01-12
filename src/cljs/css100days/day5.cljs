(ns css100days.day5
  (:require [garden.core :refer [css]]
            #_[reagent.core :as r]))

(comment 
  "Statistic: In a world of numbers, 
   it is important to be able to interpret them correctly. 
   Beautiful statistics help to capture these data.")

(def x-margin 15)

(def y-margin 20)

(def x-gap 40)


(defn line
  [index type prev-val next-val]
  [:line {:stroke-width "3px"
          :stroke (case type
                    :views "#E53935"
                    :purchase "#6200EA")
          :x1 (-> index
                  (* x-gap)
                  (+ x-margin)
                  (str "pt"))
          :y1 (-> prev-val
                  (+ y-margin)
                  (str "pt"))
          :x2 (-> index
                  inc
                  (* x-gap)
                  (+ x-margin)
                  (str "pt"))
          :y2 (-> next-val
                  (+ y-margin)
                  (str "pt"))}])

(def data
  {:views [20 40 50 60 50]
   :purchase [10 20 40 50 50]})

(def days
  ["MON" "TUE" "WED" "THU" "FRI" "SAT" "SUN"])




(defn graph-lines
  [category]
  (->> (category data)
       ((juxt butlast rest))
       (apply map #(vector %1 %2))
       (map-indexed (fn [i [prev next]]
                      (line i category prev next)))))





(def views-legend
  [:div {:class "views-legend"}
   [:div {:class "views-legend-line"}]
   [:div {:class "views-legend-text"} "Views"]])

(def purchases-legend
  [:div {:class "purchases-legend"}
   [:div {:class "purchases-legend-line"}]
   [:div {:class "purchases-legend-text"} "Purchases"]])

(def x-axis
  (->> days
       (map (fn [label]
              [:div {:class (clojure.string/lower-case label)}
               label]))
       (apply conj [:div {:id "x-axis"}])))

(defn svg-sample
  []
  (->> [[:line {:x1 "10pt"
                :y1 "30pt"
                :x2 "180pt"
                :y2 "30pt"
                :stroke "#B0BEC5"
                :stroke-width "1px"}]
        [:line {:x1 "10pt"
                :y1 "60pt"
                :x2 "180pt"
                :y2 "60pt"
                :stroke "#B0BEC5"
                :stroke-width "1px"}]
        [:line {:x1 "10pt"
                :y1 "90pt"
                :x2 "180pt"
                :y2 "90pt"
                :stroke "#B0BEC5"
                :stroke-width "1px"}]]
       (concat (graph-lines :views) (graph-lines :purchase))
       (apply conj [:svg {:position "relative"
                          :top "100px"
                          :left "200px"}])))

(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "chart-area"}
       [:div {:class "chart-head"}
        [:div {:class "title"} "WEEKLY REPORT"]
        [:div {:class "duration"} "01. FEB - 07. FEB"]]
       [:div {:class "chart-body"}
        views-legend
        purchases-legend
        [:div {:class "line-1"}]
        [:div {:class "line-2"}]
        [:div {:class "line-3"}]
        x-axis
        (svg-sample)]]]]))

