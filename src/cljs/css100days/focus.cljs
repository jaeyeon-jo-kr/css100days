(ns css100days.focus 
  (:require [reagent.core :as r]))

(defonce box1-style
  (r/atom {:width 10 :height 10
           :border "solid"}))

(defn framework
  []
  (fn []
    [:div {:class "framework"
           :onLoad (fn [_]
                      (js/console.debug "load focus framework"))}
     [:button {:class "button1" 
               :on-click (fn [e]
                           (js/console.debug "load focus framework"))
               :on-key-down (fn [e]
                              (js/console.debug e)
                              (swap! box1-style update :width inc)
                              (js/console.debug "key pressed"))
               :tab-index "2"
               } "Button 1"]
     [:button {:class "button2"
               :tab-index "1"} "Button 2"]
     [:button {:class "button3"
               :tab-index "3"} "Button 3"]
     [:div {:style @box1-style} "???"]]))

(defn event
  []
  )

