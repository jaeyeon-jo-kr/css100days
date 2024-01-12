(ns css100days.day11
  (:require [garden.core :refer [css]]))

(def ball
  [:.ball {:position "absolute"
           :left 0
           :bottom 0
           :width "100px"
           :height "100px"
           :border-radius "60%"
           :background "lightblue"}])
(def leg 
  [:.leg {:position "absolute"
          :top "-140px"
          :left "-50px"
          :width "151px"
          :height "245px"
          :transform-origin "50% 0%"}
   ball])

(def left
  [:.left
   {:animation "leg-swing 2s ease-in-out infinite"}
   [:.shoe 
    {:animation "shoe-turn 2s ease-in-out infinite"}]])

(def right
  [:.right
   {:animation "leg-swing 2s ease-in-out 1s infinite"}
   [:.shoe
    {:animation "shoe-turn 2s ease-in-out 1s infinite"}]])

(def shoe
  [:.shoe {:position "absolute"
           :left 0
           :bottom 0
           :width "151px"
           :height "130px"
           :background "url('https://100dayscss.com/codepen/doc-martens.svg') center center no-repeat"
           :transform-origin "0 95%"}])

(def floor
  [:.floor {:position "absolute"
             :background "black"
             :width "100%"
             :height "120px"
             :left "0px"
             :bottom "0px"}])

(def center
  [:.center {:position "absolute"
             :top "50%"
             :left "50%"
             :background-color "black"}])

(def frame 
  [:.frame {:position "absolute"
            :top "50%"
            :left "50%"
            :width "400px"
            :height "400px"
            :margin-top "-200px"
            :margin-left "-200px"
            :border-radius "2px"
            :box-shadow "4px 8px 16px 0 rgba(0,0,0,0.1)"
            :overflow "hidden"
            :background "radial-gradient(ellipse at center, #f6e8d7 0%, #dabe9b 100%)"
            :color "#333"
            :font-family "'Open Sans', Helvetica, sans-serif"
            :-webkit-font-smoothing "antialiased"
            :-moz-osx-font-smoothing "grayscale"}
   floor
   center])


(def leg-swing
  (str "@keyframes leg-swing {
        0%, 100% {
          transform: rotate(-22deg);
        }
        50%{
          transform: rotate(40deg);
        }
  }"))

(def shoe-turn
  (str "@keyframes shoe-turn {
    0%, 100% {
		  transform: rotate(-20deg) translateY(-5px) translateX(10px);
	  }
	  25% {
		  transform: rotate(0deg) translateY(0px) translateX(0);
	  }
	  50% {
		  transform: rotate(10deg) translateY(-10px) translateX(10px);
	  }
	  75% {
		  transform: rotate(0deg) translateY(-30px) translateX(0);
	  }
}" ))

(def animations 
  (->> [leg-swing shoe-turn]
       (interpose "\n")
       (apply str "\n")))

(defn styles
  []
  (-> (css frame shoe leg left right)
      (str animations)))

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

