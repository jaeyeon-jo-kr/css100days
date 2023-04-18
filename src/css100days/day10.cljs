(ns css100days.day10
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))

(comment 
  "Classic, noble, modern or playful? 
   What do you want your favorite watch to look like?"
  )

(def circle-in-spinner-style
  [:circle
  {:stroke "#F85B5B"
   :stroke-width "9"
   :fill "none"
   
   :stroke-dasharray "625"
   :transform-origin "center center";
   :transform "rotate(-90deg)"} ])

(def spinner-style
  [:.spinner 
   {:position "absolute"
    :width "350px"
    :height "350px"
    :border-radius "50%"
    :top "-50px"
    :left "0px"}
   circle-in-spinner-style])

(def moving-circle-style
  [:.moving-circle
   {:position "absolute"
    :top "0.5%"
    :left "0.5%"
    :border "6px solid red"
    :border-radius "50%"
    :stroke-dasharray "625"
    :stroke-dashoffset "0"
    :animation "spin 4s linear infinite"
    :transform-origin "center center"
    :width "168pt"
    :height "168pt"}])

(def spin-keyframe
  (str "\n@keyframes spin { 
  0% {
    transform: rotate(-90deg)
  }
}"))

(def dot-circle-style
  [:.dot-circle
   {:position "absolute"
    :color "transparent"
    :top "4%"
    :left "4%"
    :border "3px dotted black"
    :border-radius "50%"
    :width "160pt"
    :height "160pt"}])

(def inner-circle-style
  [:.inner-circle
   {:position "absolute"
    :top "5%"
    :left "5%"
    :width "180pt"
    :height "180pt"
    :border-radius "50%"
    :background "#f3ffbd"}
   dot-circle-style
   ])


(def outer-circle-style
  [:.outer-circle
   {:position "absolute"
    :top "-100pt"
    :left "-100pt"
    :width "200pt"
    :height "200pt"
    :border-radius "50%"
    :background "#e6999c"}
   inner-circle-style])

(defn styles
  []
  (fn []
    (-> (->> [[:.frame {:position "absolute"
                        :top "50%"
                        :left "50%"
                        :width "400px"
                        :height "400px"
                        :margin-top "-200px"
                        :margin-left "-200px"
                        :border-radius "2px"
                        :box-shadow "4px 8px 16px 0 rgba(0,0,0,0.1)"
                        :overflow "hidden"
                        :background "#a7dadc"
                        :color "#333"
                        :font-family "'Open Sans', Helvetica, sans-serif"
                        :-webkit-font-smoothing "antialiased"
                        :-moz-osx-font-smoothing "grayscale"}]
              [:.center {:position "absolute"
                         :top "50%"
                         :left "50%"
                         :transform "translate(-50%,-50%)"}
               outer-circle-style]
              spinner-style]
             (apply css))
             (str spin-keyframe)
             )))

(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "outer-circle"}
       [:div {:class "inner-circle"}
        [:div {:class "dot-circle"}]
        [:svg {:class "spinner"
               :viewbox "0 0 200 200"
               :xlmns "https://www.w3.org/2000/svg"}
         [:circle {:cx "180" :cy "120" 
                   :r "98"}]]]]]]))

