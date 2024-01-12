(ns css100days.core 
  (:require 
   [css100days.basic :as basic]
   [css100days.day1 :as day1]
   [css100days.day2 :as day2]
   [css100days.day3 :as day3]
   [css100days.day4 :as day4]
   [css100days.day5 :as day5]
   [css100days.day6 :as day6]
   [reitit.ring :as ring]
   [muuntaja.core :as m]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [ring.adapter.jetty :as ring-jetty]))


(def app
  (ring/ring-handler
   (ring/router
    ["/"
     ["css/"
      ["day1.css" 
       {:get (fn [_]
               {:status 200
                :body (day1/styles)})}]
      ["basic.css"
       {:get (fn [_]
               {:status 200
                :body (basic/styles)})}]
      ["day2.css"
       {:get (fn [_]
               {:status 200
                :body (day2/styles)})}]
      ["day3.css"
       {:get (fn [_]
               {:status 200
                :body (day3/styles)})}]
      ["day4.css"
       {:get (fn [_]
               {:status 200
                :body (day4/styles)})}]
      ["day6.css"
       {:get (fn [_]
               {:status 200
                :body (day6/styles)})}]]
     ["api/"]
     ["assets/*" (ring/create-resource-handler {:root "public/assets"})]]
    {:data {:muuntaja m/instance
            :middleware [muuntaja/format-middleware]}})))

(defn start []
  (ring-jetty/run-jetty #'app {:port  3001
                               :join? false}))


(defonce server (start))

