(ns css100days.day11
  )



(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "leg left"}
       [:p {:class "shoe"}]]
      [:div {:class "leg right"}
       [:p {:class "shoe"}]]]
     [:div {:class "floor"}]]))

