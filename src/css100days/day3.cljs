(ns css100days.day3
  (:require [garden.core :refer [css]]
            [reagent.core :as r]
            [cljs.core :as core]))

;;;;The Pyramide: Not as challenging as the real pyramids in Egypt, but the shadow path is not easy.

(def style-status
  (r/atom [[:.frame {:position "absolute"
                     :top "50%"
                     :left "50%"
                     :width "400px"
                     :height "400px"
                     :margin-top "-200px"
                     :margin-left "-200px"
                     :border-radious "2px"
                     :box-shadow "4px 8px 16px 0 rgba(0,0,0,0.1)"
                     :overflow "hidden"
                     :background "#272C34"
                     :color "#333"
                     :font-family "'Open Sans', Helvetica, sans-serif"
                     :-webkit-font-smoothing "antialiased"
                     :-moz-osx-font-smoothing "grayscale"}]
           [:.center {:position "absolute"
                      :top "50%"
                      :left "50%"
                      :transform "translate(-50%,-50%)"}]
           [:.circle {:position "relative"
                      :top "0"
                      :left "0"
                      :width "180px"
                      :height "180px"
                      :clip-path "circle(40%)"
                      :z-index 10}]
           [:.sky {:position "absolute"
                   :background-color "#7DDFFC"
                   :z-index 2
                   :width "180px"
                   :height "120px"}]
           [:.sun {:position "absolute"
                   :background-color "#F0DE95"
                   :z-index 5
                   :top "60px"
                   :left "20px"
                   :width "30px"
                   :height "30px"
                   :border-radius "50%"}]
           [:.left-side {:position "absolute"
                        :clip-path "polygon(31% 0%, 0% 100%, 100% 100%)"
                         :z-index 8
                         :background "#F4F4F4"
                         :top "80px"
                         :left "55px"
                         :width "80px"
                         :height "40px"}]
           [:.right-side {:position "absolute"
                         :clip-path "polygon(0% 0%, 30% 100%, 100% 100%)"
                         :z-index 8
                         :background "#DDDADA"
                         :top "80px"
                         :left "80px"
                         :width "58px"
                         :height "40px"}]
           [:.shadow {:position "absolute"
                      :clip-path "polygon(0% 0%, 100% 0%, 30% 100%)"
                      :z-index 8
                      :background "rgba(0,0,0,0.2)"
                      :top "120px"
                      :left "55px"
                      :width "80px"
                      :height "20px"}]
           [:.ground {:position "absolute"
                      :background-color "#F0DE75"
                      :z-index 3
                      :top "120px"
                      :width "180px"
                      :height "80px"}]]))

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

(defn update-shadow-clip-path
  []
  (let [shadow-index (find-index @style-status :.shadow)]
    (swap! style-status
           (fn [status]
             (assoc-in status
                       [shadow-index css-attribute-index :clip-path]
                       (shadow-clip-path))))))

(defn update-sun-position
  []
  (let [sun-index (find-index @style-status :.sun)]
    (swap! style-status
           (fn [status]
             (-> status
                 (assoc-in [sun-index css-attribute-index :left] 
                           (-> (sun-position @sun-degree)
                               :left))
                 (assoc-in [sun-index css-attribute-index :top] 
                           (-> (sun-position @sun-degree)
                               :top)))))))

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

(defn update-sides-color
  []
  (let [left-side-index (find-index @style-status :.left-side)
        right-side-index (find-index @style-status :.right-side)]
    (swap! style-status
           (fn [status]
             (-> status
                 (assoc-in [left-side-index css-attribute-index :background] (left-side-color))
                 (assoc-in [right-side-index css-attribute-index :background] (right-side-color)))))))

(def interval 100)

(defn update-sun-degree
  []
  (if (< 0.5 @sun-degree)
    (reset! sun-degree -1.0)
    (swap! sun-degree + 0.03)))

(defn update-animation
  [] 
  (update-sides-color)
  (update-shadow-clip-path)
  (update-sun-degree)
  (update-sun-position))


(defn styles
  []
  (fn []
    (apply css @style-status)))


(defonce time-updater
  (js/setInterval update-animation interval))


(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "circle"}
       [:div {:class "sky"}]
       [:div {:class "sun"}]
       [:div {:class "ground"}]
       [:div {:class "left-side"}]
       [:div {:class "right-side"}]
       [:div {:class "shadow"}]]]]))
