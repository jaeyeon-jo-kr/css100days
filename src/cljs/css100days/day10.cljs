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
   :stroke-width "11"
   :fill "none"
   :stroke-dasharray "625"
   :transform-origin "center center"
   :animation "spin 4s linear infinite"
   :transform "rotate(-90deg)"} ])

(def spin-keyframe
  (str "\n@keyframes spin { 
  from {
		stroke-dashoffset: 625;
		transform: rotate(-90deg) scaleY(1);
	}
	50% {
		stroke-dashoffset: 0;
		transform: rotate(-90deg) scaleY(1);
	}
	50.001% {
		transform: rotate(-90deg) scaleY(-1);
	}
	to {
		stroke-dashoffset: 625;
		transform: rotate(-90deg) scaleY(-1);
	}
}"))

(def spinner-style
  [:.spinner 
   {:position "absolute"
    :width "270px"
    :height "270px"
    :top "-15px"
    :left "-15px"}
   circle-in-spinner-style])

(def current-date-style
  [:.current-date
   {:position "absolute"
    :top "60px"
    :left "52px"
    :width "270px"
    :height "270px"}])

(def current-time-style
  [:.current-time
   {:position "absolute"
    :top "24px"
    :left "42px"
    :font-size "60px"}])

(def beat-style
  [:.beat
   {:position "absolute"
    :top "130px"
    :left "60px"
    :font-size "20px"}])

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

(def energy-style
  [:.energy
   {:position "absolute"
    :top "105pt"
    :left "80pt"
    :font-size "11.3pt"}])


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
   current-date-style
   current-time-style
   beat-style
   energy-style])



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

