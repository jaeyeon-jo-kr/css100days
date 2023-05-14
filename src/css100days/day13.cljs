(ns css100days.day13
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))

(defn styles
  []
  (css [:.frame {:position "absolute"
                 :top "50%"
                 :left "50%"
                 :width "400px"
                 :height "400px"
                 :margin-top "-200px"
                 :margin-left "-200px"
                 :border-radius "2px"
                 :box-shadow "4px 8px 16px 0 rgba(0,0,0,0.1)"
                 :overflow "hidden"
                 :background "#fff"
                 :color "#333"
                 :font-family "'Open Sans', Helvetica, sans-serif"
                 :-webkit-font-smoothing "antialiased"
                 :-moz-osx-font-smoothing "grayscale"}]
       [:.center {:position "absolute"
                  :top "50%"
                  :left "50%"
                  :transform "translate(-50%,-50%)"}]))

(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:p "Happy coding :)"]]]))



(comment 
  
  (defprotocol Component
    (style [this])
    (element [this])
   )
  
  (defrecord MyComponent [style-code element-code]
    Component
   (style [this] style-code) 
    (element [this] element-code)
   )
  
  (Component nil)

  )
