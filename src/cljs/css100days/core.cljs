(ns css100days.core
  (:require [css100days.basic :as basic]
            [css100days.day1 :as day1]
            [css100days.day10 :as day10]
            [css100days.day11 :as day11]
            [css100days.day12 :as day12]
            [css100days.day13 :as day13]
            [css100days.day14 :as day14]
            [css100days.day15 :as day15]
            [css100days.day19 :as day19]
            [css100days.day2 :as day2]
            [css100days.day29 :as day29]
            [css100days.day3 :as day3]
            [css100days.day4 :as day4]
            [css100days.day5 :as day5]
            [css100days.day6 :as day6]
            [css100days.day7 :as day7]
            [css100days.day8 :as day8]
            [css100days.day9 :as day9]
            [css100days.flex-box :as flex-box]
            [css100days.focus :as focus]
            [css100days.selector :as selector]
            [reagent.core :as r]
            [reagent.dom :as re-dom]
            [reitit.coercion.schema :as rsc]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]))

(defonce match (r/atom nil))

(def server-domain "http://localhost:3001/")

(defn log-fn [& params]
  (fn [_]
    (apply js/console.log params)))

(def raw-routes
  ["/"
   [""
    {:name ::home
     :view basic/framework
     :css "day1"}]
   ["basic"
    {:name ::basic
     :view basic/framework
     :css "basic"}]
   ["day1"
    {:name ::day1
     :view day1/framework
      :css "day1"}]
   ["day2"
    {:name ::day2
     :view day2/framework 
     :css "day2"}]
   ["day3"
    {:name ::day3
     :view day3/framework
     :css "day3"}]
   ["day4"
    {:name ::day4
     :view day4/framework
     :css "day4"}]
   ["day5"
    {:name ::day5
     :view day5/framework
     :css "day5"}]
   ["day6"
    {:name ::day6
     :view day6/framework
     :css "day6"}]
   ["day7"
    {:name ::day7
     :view day7/framework
     :css "day7"}]
   ["day8"
    {:name ::day8
     :view day8/framework
     :css "day8"}]
   ["day9"
    {:name ::day9
     :view day9/framework
     :css "day9"}]
   ["day10"
    {:name ::day10
     :view day10/framework
     :css "day10"}]
   ["day11"
    {:name ::day11
     :view day11/framework
     :css "day11"}]
   ["day12"
    {:name ::day12
     :view day12/framework
     :css "day12"}]
   ["day13"
    {:name ::day13
     :view day13/framework
     :css "day13"}]
   ["day14"
    {:name ::day14
     :view day14/framework
     :css "day14"}]
   ["day15"
    {:name ::day15
     :view day15/framework
     :css "day15"}]
   ["day19"
    {:name ::day19
     :view day19/view
     :css "day19"}]
   ["flex-box"
    {:name ::flex-box
     :view flex-box/framework
     :css "flex-box"}]
   ["selector"
    {:name ::selector
     :view selector/view
     :style selector/styles}]
   ["focus"
    {:name ::focus
     :view focus/framework
     :event-fn focus/event
     :css "focus"}]
   ["day29"
    {:name ::day29
     :view day29/framework
     :css "day29"}]])

(def routes
  (rf/router
   raw-routes
   {:data {:controllers [{:start (log-fn "start" "root-controller")
                          :stop (log-fn "stop" "root-controller")}]
           :coercion rsc/coercion}}))

(defn current-module-name
  []
  (-> @match
      :data
      :name
      name))

(defn select-component
  []
  (->> raw-routes rest
       (map (comp
             (fn [name]
               [:option {:value name} name])
             name
             :name
             second))
       (apply conj
              [:select {:name "navigation-select"
                        :value (current-module-name)
                        :on-change (fn [e]
                                     (set! js/document.location
                                           (str "/"
                                                (-> e
                                                    .-target
                                                    .-value))))}])))




(defn navigation-bar
  []
  [:div
   (select-component)])

(defn current-page
  []
  [:div
   (navigation-bar)
   (when @match
     [:div
      [(:view (:data @match))]])])

(defn render-body
  [] 
  (re-dom/render 
   [current-page]
   (js/document.getElementById "app")))



(defn init-router
  []
  (rfe/start! routes (fn [m] (reset! match m))
   {:use-fragment false}))

(defn render-css
  []
  (-> (js/document.getElementById "css")
      (.setAttribute "href" (str server-domain "css/" (-> @match :data :css) ".css"))))

(defn register-events
  []
  (when-let [event-fn (-> @match :data :event-fn)]
    (event-fn)))

(defn render
  []
  (render-css) 
  (render-body)
  (register-events))

(defn ^:export init
  [& _params] 
  (init-router)
  (render))

(defn ^:dev/after-load start [] 
  (init-router)
  (render))