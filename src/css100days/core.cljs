(ns css100days.core
  (:require
   [reagent.dom :as re-dom]
   [css100days.day11 :as basic]
   [css100days.day1 :as day1]
   [css100days.day2 :as day2]
   [css100days.day3 :as day3]
   [css100days.day4 :as day4]
   [css100days.day5 :as day5]
   [css100days.day6 :as day6]
   [css100days.day7 :as day7]
   [css100days.day8 :as day8]
   [css100days.day9 :as day9]
   [css100days.day10 :as day10]
   [css100days.day11 :as day11]
   [reagent.core :as r]
   [reitit.frontend :as rf]
   [reitit.frontend.easy :as rfe]
   [reitit.coercion.schema :as rsc]))

(defonce match (r/atom nil))

(defn log-fn [& params]
  (fn [_]
    (apply js/console.log params)))

(def raw-routes
  ["/"
   [""
    {:name ::home
     :view basic/framework
     :style basic/styles}]
   ["basic"
    {:name ::basic
     :view basic/framework
     :style basic/styles}]
   ["day1"
    {:name ::day1
     :view day1/framework
     :style day1/styles}]
   ["day2"
    {:name ::day2
     :view day2/framework
     :style day2/styles}]
   ["day3"
    {:name ::day3
     :view day3/framework
     :style day3/styles}]
   ["day4"
    {:name ::day4
     :view day4/framework
     :style day4/styles}]
   ["day5"
    {:name ::day5
     :view day5/framework
     :style day5/styles}]
   ["day6"
    {:name ::day6
     :view day6/framework
     :style day6/styles}]
   ["day7"
    {:name ::day7
     :view day7/framework
     :style day7/styles}]
   ["day8"
    {:name ::day8
     :view day8/framework
     :style day8/styles}]
   ["day9"
    {:name ::day9
     :view day9/framework
     :style day9/styles}]
   ["day10"
    {:name ::day10
     :view day10/framework
     :style day10/styles}]
   ["day11"
    {:name ::day11
     :view day11/framework
     :style day11/styles}]])

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
               [:option {:value name
                         :selected (when (= (current-module-name) name)
                                     true)}
                name])
             name
             :name
             second))
       (apply conj
              [:select {:name "navigation-select"
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

(defn current-style
  []
  (when @match
    (:style (:data @match))))


(defn render-style
  []
  (re-dom/render
   [current-style]
   (js/document.getElementById "style")))


(defn render-body
  [] 
  (re-dom/render 
   [current-page]
   (js/document.getElementById "app")))



(defn init-router
  []
  (rfe/start!
   routes
   (fn [m]
     (reset! match m))
   {:use-fragment false}))

(defn render
  []
  (render-style)
  (render-body))

(defn ^:export init
  [& _params] 
  (init-router)
  (render))

(defn ^:dev/after-load start [] 
  (init-router)
  (render))