(ns css100days.core 
  (:require [css100days.day11 :as day11]
            [css100days.basic :as basic]
            [css100days.day1 :as day1]
            [css100days.day10 :as day10]
            [css100days.day2 :as day2]
            [css100days.day3 :as day3]
            [css100days.day4 :as day4]
            [css100days.day5 :as day5]
            [css100days.day6 :as day6]
            [css100days.day7 :as day7]
            [css100days.day8 :as day8]
            [css100days.day9 :as day9]
            [css100days.day12 :as day12]
            [muuntaja.core :as m]
            [reitit.ring :as ring]
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
      ["day5.css"
       {:get (fn [_]
               {:status 200
                :body (day5/styles)})}]
      ["day6.css"
       {:get (fn [_]
               {:status 200
                :body (day6/styles)})}]
      ["day7.css"
       {:get (fn [_]
               {:status 200
                :body (day7/styles)})}]
      ["day8.css"
       {:get (fn [_]
               {:status 200
                :body (day8/styles)})}]
      ["day9.css"
       {:get (fn [_]
               {:status 200
                :body (day9/styles)})}]
      ["day10.css"
       {:get (fn [_]
               {:status 200
                :body (day10/styles)})}]
      ["day11.css"
       {:get (fn [_]
               {:status 200
                :body (day11/styles)})}]
      ["day12.css"
       {:get (fn [_]
               {:status 200
                :body (day12/styles)})}]]
     ["api/"]
     ["assets/*" (ring/create-resource-handler {:root "public/assets"})]]
    {:data {:muuntaja m/instance
            :middleware [muuntaja/format-middleware]}})))

(defn start []
  (ring-jetty/run-jetty #'app {:port  3001
                               :join? false}))


(defonce server (start))

