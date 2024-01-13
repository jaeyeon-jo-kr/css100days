(ns css100days.day10
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))



(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "outer-circle"}
       [:div {:class "inner-circle"}
        [:div {:class "dot-circle"}]
        [:p {:class "current-date"} 
         "MON 15 JAN 2015"]
        [:p {:class "current-time"}
         "11:42"]
        [:p {:class "beat"}
         "❤️81"]
        [:p {:class "energy"}
         "1248 KCAL"]
        [:svg {:class "spinner"
               :xlmns "https://www.w3.org/2000/svg"}
         [:circle {:cx "135" :cy "135"
                   :pathLength "100"
                   :r "115"}]]]]]]))

