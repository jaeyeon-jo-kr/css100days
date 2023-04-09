(ns css100days.day6
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))

(def name-style
  [:.name {:position "relative"
           :top "20px"
           :left "0px"
           :font-size "15px"
           :font-weight "600"
           :margin-top "20px"}])

(def job-style 
  [:.job {:position "relative"
          :left "0px"
          :font-size "15px"
          :margin-top "20px"}])

(def actions-style
  [:.actions {:position "relative"
              :margin-top "20px"
              :left "0px"}
   [:.btn {:position "relative"
           :display "block"
           :width "120px"
           :height "30px"
           :background "none"
           :border-radius "15px"
           :margin "0 auto 10px auto"}
    ["&:hover" {:background "pink"
                :color "#fff"
                :cursor "pointer"}]]])

(def image-style
  [:.image {:position "relative"
            :top "60px"
            :left "70px"
            :width "100px"
            :height "100px"}
   [:.inner-circle {:position "absolute"
                    :box-sizing "border-box"
                    :width "55px"
                    :height "55px"
                    :top "10px"
                    :left "5px"
                    :border-width "1px"
                    :border-style "solid"
                    :border-color "blue blue blue transparent"
                    :border-radius "50%"
                    :transition "all 1.5s ease-in-out"}]
   [:.outer-circle {:position "absolute"
                    :box-sizing "border-box"
                    :top "4px"
                    :left "0px"
                    :width "66px"
                    :height "66px"
                    :border-width "1px"
                    :border-style "solid"
                    :border-color "#ee3366 transparent #ee3366 #ee3366"
                    :border-radius "50%"
                    :transition "all 1.5s ease-in-out"}]
   ["&:hover" {:cursor "pointer"}
    [:.outer-circle {:transform "rotate(-360deg)"}]
    [:.inner-circle {:transform "rotate(360deg)"}]
    ]])

(def profile-style
  [:.profile {:float "left"
              :width "200px"
              :height "297px"
              :display "block"
              :background "#f2cfdc"
              :text-align "center"}
   image-style
   name-style
   job-style
   actions-style])

(def stats-box-style
  [:.box {:box-sizing "border-box"
         :width "120px"
         :height "99px"
          :background "#e6f2ea"
         :text-align "center"
         :padding-top "28px"}
   ["&:hover"
    {:background "#ff6b6b"}]
   ["&:nthchild(2)"
    {:margin "1px 0"}]
   [:.value {:display "block"
             :font-size "18px"
             :font-weight "600px"}]
   [:.parameter {:display "block"
                 :font-size "11px"}]])

(def stats-style
  [:.stats {:float "left"
            :display "block"}
   stats-box-style])



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
                  :background "#bae5d6"
                  :color "#333"
                  :font-family "'Open Sans', Helvetica, sans-serif"}]
        [:.center {:position "absolute"
                   :top "50%"
                   :left "50%"
                   :height "299px"
                   :width "320px"
                   :overflow "hidden"
                   :border-radius "3px"
                   :transform "translate(-50%,-50%)" 
                   :box-shadow "10px 10px 15px 0 rgba(0,0,0,0.3)"}]
        profile-style
        stats-style]
       (apply css)))

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

