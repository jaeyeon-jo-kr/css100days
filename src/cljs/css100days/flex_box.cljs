(ns css100days.flex-box
  (:require [garden.core :as g :refer [css]]))


(defn framework
  []
  (fn []
    [:div {:class "framework"}
     [:div {:class "example-1"}
      [:div {:class "flex-1"}"flex-1"]
      [:div {:class "other-flex-1"}"other"]
      [:div {:class "other-flex-1"}"other"]]
     [:div {:class "example-1"}
      [:div {:class "flex-2"} "flex-2"]
      [:div {:class "other-flex-1"} "other"]
      [:div {:class "other-flex-1"} "other"]]
     [:div {:class "example-1"}
      [:div {:class "flex-1-30px"} "flex-1-30px"]
      [:div {:class "other-flex-1"} "other"]
      [:div {:class "other-flex-1"} "other"]]
     [:div {:class "example-1"}
      [:div {:class "flex-1-1-100px"} "flex-1-1-100px"]
      [:div {:class "other-flex-1"} "other"]
      [:div {:class "other-flex-1"} "other"]]]))

