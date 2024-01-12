(ns css100days.basic
  (:require [garden.core :refer [css]]))



(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:p "Happy coding :)"]]]))

