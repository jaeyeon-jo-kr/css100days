(ns css100days.day6)


(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "profile"}
       [:div {:class "image"}
        
        [:div {:class "inner-circle"}]
        [:div {:class "outer-circle"}]]
       [:p {:class "name"} "Sarah"]
       [:p {:class "job"} "Producer"]
       [:div {:class "actions"}
        [:button {:class "btn"} "follow"]
        [:button {:class "btn"} "message"]]]
      [:div {:class "stats"}
       [:div {:class "box"}
        [:span {:class "value"} "10"]
        [:span {:class "parameter"} "follwers"]]
       [:div {:class "box"}
        [:span {:class "value"} "20"]
        [:span {:class "parameter"} "posts"]]
       [:div {:class "box"}
        [:span {:class "value"} "20"]
        [:span {:class "parameter"} "followings"]]]
      #_[:p "Happy coding :)"]]]))

