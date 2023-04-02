(ns css100days.core
  (:require
   [reagent.dom :as re-dom]
   [css100days.basic :as basic]
   [css100days.day1 :as day1]
   [css100days.day2 :as day2]
   [reagent.core :as r]
   [reitit.frontend :as rf]
   [reitit.frontend.easy :as rfe]
   [reitit.coercion.schema :as rsc]))




(defonce match (r/atom nil))

(defn navigation-bar
  []
  [:div
   [:select {:name "navigation-select"}
    [:option {:value "aaa"}]]
   [:input {:type "button"
            :on-click (fn [])
            :value "Click to change."}]])

(comment 
  (re-dom/render
   [navigation-bar]
   (js/document.getElementById "app")))

(defn current-page
  []
  [:div
   (navigation-bar)
   (when @match
     [:div
      [(:view (:data @match)) #_@match]])])
(comment
  

  )

(defn current-style
  []
  (when @match
    (:style (:data @match))))


(defn render-style
  []
  (re-dom/render
   [current-style]
   (js/document.getElementById "style")))

(comment
  (render-style)
  )

(defn render-body
  [] 
  (re-dom/render 
   [current-page]
   (js/document.getElementById "app")))

(defn log-fn [& params]
  (fn [_]
    (apply js/console.log params)))

(def routes
  (rf/router
   ["/"
    [""
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
      :style day2/styles}]]
   {:data {:controllers [{:start (log-fn "start" "root-controller")
                          :stop (log-fn "stop" "root-controller")}]
           :coercion rsc/coercion}}))

(defn init-router
  []
  (rfe/start!
   routes
   (fn [m]
     (js/console.debug "Initial route : " m)
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