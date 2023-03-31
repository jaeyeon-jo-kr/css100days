(ns css100days.core
  (:require [garden.core :refer [css]]
            [reagent.dom :as re-dom]
            [css100days.day1 :as day1]))
(defn styles 
  []
  (css [:.frame {:position "absolute"
                :top "50%"
                :left "50%"
                :width "400px"
                :height "400px"
                :margin-top "-200px"
                :margin-left "-200px"
                :border-radious "2px"
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

(defn render-style
  []
  (re-dom/render
   [day1/styles]
   (js/document.getElementById "style")))

(defn render-body
  [] 
  (re-dom/render [day1/framework]
                 js/document.body))

(defn render
  []
  (render-style)
  (render-body))

(defn ^:export init
  [& _params] 
  (render))

(defn ^:dev/after-load start [] 
  (render))