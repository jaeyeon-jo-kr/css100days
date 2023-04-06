(ns css100days.day5
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))

(comment 
  "Statistic: In a world of numbers, 
   it is important to be able to interpret them correctly. 
   Beautiful statistics help to capture these data.")

(def views-line-style "4px solid #E53935")

(def views-legend-style
  [:.views-legend
   {:position "absolute"
    :top "5pt"
    :left "62pt"
    :width "60pt"
    :height "20pt"}])

(def views-legend-line-style
  [:.views-legend-line
   {:position "absolute"
    :top "4pt"
    :left "20pt"
    :width "15pt"
    :border-top views-line-style}])

(def views-legend-text-style
  [:.views-legend-text
   {:position "absolute"
    :font-size "8pt"
    :top "0pt"
    :left "40pt"}])

(def views-legend
  [:div {:class "views-legend"}
   [:div {:class "views-legend-line"}]
   [:div {:class "views-legend-text"} "Views"]])

(def purchases-line-style "4px solid #6200EA")

(def purchases-legend-style
  [:.purchases-legend
   {:position "absolute"
    :top "5pt"
    :left "110pt"
    :width "60pt"
    :height "20pt"}])

(def purchases-legend-line-style
  [:.purchases-legend-line
   {:position "absolute"
    :top "4pt"
    :left "20pt"
    :width "15pt"
    :border-top purchases-line-style}])

(def purchases-legend-text-style
  [:.purchases-legend-text
   {:position "absolute"
    :font-size "8pt"
    :top "0pt"
    :left "40pt"}])

(def purchases-legend
  [:div {:class "purchases-legend"}
   [:div {:class "purchases-legend-line"}]
   [:div {:class "purchases-legend-text"} "Purchases"]])

(def x-axis-label-gap 35)

(def days
  ["MON" "TUE" "WED" "THU" "FRI" "SAT" "SUN"])

(def x-axis-style
  (->> [days (->> (iterate (partial + x-axis-label-gap) 14)
             (take 7))]
       (apply mapv (fn [label left]
                     [(-> (str "." (clojure.string/lower-case label))
                          keyword)
                      {:position "absolute"
                       :top "128px"
                       :color "#556270"
                       :font-size "9pt"
                       :left (str left "px")}]))))

(def x-axis
  (->> days
       (map (fn [label]
              [:div {:class (clojure.string/lower-case label)}
               label]))
       (apply conj [:div {:id "x-axis"}])))



(defn styles
  []
  (->> [[:.frame {:position "absolute"
                  :top "50%"
                  :left "50%"
                  :width "400px"
                  :height "400px"
                  :margin-top "-200px"
                  :margin-left "-200px"
                  :border-radius "2px"
                  :box-shadow "4px 8px 16px 0 rgba(0,0,0,0.1)"
                  :overflow "hidden"
                  :background "#7888FF"
                  :color "#333"
                  :font-family "'Open Sans', Helvetica, sans-serif"
                  :-webkit-font-smoothing "antialiased"
                  :-moz-osx-font-smoothing "grayscale"}]
        [:.center {:position "absolute"
                   :top "50%"
                   :left "50%"
                   :transform "translate(-50%,-50%)"}]
        [:.chart-area
         {:position "absolute"
          :top "-100pt"
          :left "-100pt"
          :width "200pt"
          :height "180pt"}]
        [:.chart-head {:position "relative"
                       :top "0pt"
                       :left "0pt"
                       :width "100%"
                       :height "30%"
                       :background "#4EC5F1"}]
        [:.chart-body
         {:position "relative"
          :width "100%"
          :height "70%"
          :background "#FCE4EC"}]
        [:.title
         {:position "absolute"
          :top "10pt"
          :left "5pt"
          :font-size "12pt"
          :color "#FFFFFF"
          :font-style "italic; bold;"}]
        [:.duration
         {:position "absolute"
          :top "30pt"
          :left "5pt"
          :font-size "12pt"
          :color "#FFFFFF"
          :font-style "bold;"}]
        [:.line-1
         {:position "absolute"
          :top "30pt"
          :left "15pt"
          :width "170pt"
          :height "40pt"
          :border-top "1px solid #B0BEC5;"}]
        [:.line-2
         {:position "absolute"
          :top "60pt"
          :left "15pt"
          :width "170pt"
          :height "40pt"
          :border-top "1px solid #B0BEC5;"}]
        [:.line-3
         {:position "absolute"
          :top "90pt"
          :left "15pt"
          :width "170pt"
          :height "40pt"
          :border-top "1px solid #B0BEC5;"}]
        views-legend-style
        views-legend-line-style
        views-legend-text-style
        purchases-legend-style
        purchases-legend-line-style
        purchases-legend-text-style]
       (concat x-axis-style)
       (apply css)))

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
        x-axis]]]]))

