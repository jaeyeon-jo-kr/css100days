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
     :style day2/styles}]])

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
                                                    .-value)))
                                     
                                     )}]))
  )




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
      [(:view (:data @match)) #_@match]])])

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