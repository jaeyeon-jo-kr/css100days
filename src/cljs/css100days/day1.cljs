(ns css100days.day1
  (:require))


(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "number"}
       [:div {:class "one-one"}]
       [:div {:class "one-two"}]
       [:div {:class "zero-one"}]
       [:div {:class "zero-two"}]]
      [:span {:class "big"} "Days"]
      [:span {:class "small"} "CSS Challenge"]]]))
