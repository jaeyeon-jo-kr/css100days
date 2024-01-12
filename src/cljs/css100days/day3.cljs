(ns css100days.day3
  (:require [garden.core :refer [style]]
            [reagent.core :as r]
            [cljs.core :as core]))

;;;;The Pyramide: Not as challenging as the real pyramids in Egypt, but the shadow path is not easy.

(defn find-index
  [style-status key]
  (->> style-status
       (keep-indexed (fn [i [style-key]]
                       (when (= style-key key)
                         i)))
       first))

(def sun-center-left 63)
(def sun-center-top 60)

(def sun-radius 65)

(def css-attribute-index 1)

(def sun-degree (r/atom -1.0))

(defn sun-position
  [degree]
  {:left (-> degree
             (* Math/PI)
             Math/cos
             (* sun-radius)
             (+ sun-center-left)
             (str "px"))
   :top (-> degree
            (* Math/PI)
            Math/sin
            (* sun-radius)
            (+ sun-center-top)
            (str "px"))})


(defn shadow-clip-path
  []
  (let [top (-> @sun-degree
                -
                (* Math/PI)
                Math/sin
                (* 60)
                (+ 30))
        left (-> @sun-degree
                 -
                 (* Math/PI)
                 Math/cos
                 (* 60)
                 (+ 30))]
    (str "polygon(0% 0%, 100% 0%, " top "% " left"%)")))


(defn left-side-color
  []
  (if (> @sun-degree -0.1)
    "#DDDADA"
    "#F4F4F4"))

(defn right-side-color
  []
  (if (> @sun-degree -0.1)
    "#F4F4F4"
    "#DDDADA"))


(def interval 100)

(defn update-sun-degree
  []
  (if (< 0.5 @sun-degree)
    (reset! sun-degree -1.0)
    (swap! sun-degree + 0.03)))

(defn update-animation
  [] 
  (update-sun-degree))


#_(defonce time-updater
  (js/setInterval update-animation interval))


(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "circle"}
       [:div {:class "sky"}]
       [:div {:class "sun"
              :style {:left (:left (sun-position @sun-degree))
                      :top (:top (sun-position @sun-degree))}}]
       [:div {:class "ground"}]
       [:div {:class "left-side"
              :style {:background (left-side-color)}}]
       [:div {:class "right-side"
              :style {:background (right-side-color)}}]
       [:div {:class "shadow"
              :style {:clip-path (shadow-clip-path)}}]]]]))
