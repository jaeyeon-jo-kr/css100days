(ns css100days.day2
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))

(defonce positions
  (r/atom 
   {:line1 {:degree 0
            :top 0}
    :line2 {:degree 0
            :top 25
            :visibility "visible"}
    :line3 {:degree 0
            :top 50}}))

(defonce status (r/atom :ready))

(defn update-position
  []
  (case @status
    :three-bar->center
    (if (= (-> @positions :line1 :top)
           (-> @positions :line2 :top)
           (-> @positions :line3 :top))
      (reset! status :center->x) 
      (do
        (swap! positions #(update-in % [:line1 :top] inc))
        (swap! positions #(update-in % [:line3 :top] dec))))
    :ready nil
    :center->x 
    (if  (and (= (-> @positions :line1 :degree) 45)
              (= (-> @positions :line3 :degree) -45))
      (reset! status :ready)
      (do
        (swap! positions #(assoc-in % [:line2 :visibility] "hidden"))
        (swap! positions #(update-in % [:line1 :degree] inc))
        (swap! positions #(update-in % [:line3 :degree] dec))))
    
    
    nil))

(defn transform 
  [line-key]
  (str "rotate(" (-> @positions
                     line-key
                     :degree) "deg)"))

(defonce time-updater 
  (js/setInterval update-position 10))

(defn top
  [line-key]
  (-> @positions
      line-key
      :top))


(defn styles
  []
  (fn [] 
    (css [:.frame {:position "absolute"
                   :top "50%"
                   :left "50%"
                   :width "400px"
                   :height "400px"
                   :margin-top "-200px"
                   :margin-left "-200px"
                   :border-radius "2px"
                   :box-shadow "4px 8px 16px 0 rgba(0,0,0,0.1)"
                   :overflow "hidden"
                   :background "#3FAF82"
                   :color "#fff"
                   :font-family "'Open Sans', Helvetica, sans-serif"
                   :-webkit-font-smoothing "antialiased"
                   :-moz-osx-font-smoothing "grayscale"}]
         [:.center {:position "absolute"
                    :top "50%"
                    :left "50%"
                    :transform "translate(-50%,-50%)"}]
         [:.position {:position "relative"
                      :height "100px"
                      :width "100px"}
          [:.line1 {:position "absolute"
                    :z-index 10
                    :top (top :line1)
                    :left "0"
                    :background "#fff"
                    :border-radius "3px"
                    :height "8px"
                    :width "80px"
                    :transform (transform :line1)
                    :border "24px #fff"}]
          [:.line2 {:position "absolute"
                    :z-index 11
                    :top (top :line2)
                    :left "0"
                    :background "#fff"
                    :border-radius "3px"
                    :height "8px"
                    :width "80px"
                    :border "24px #fff"
                    :visibility (-> @positions :line2 :visibility)
                    :transform (transform :line2)
                    :box-shadow "0 0 13px 0 rgba(0,0,0,0.2)"}]
          [:.line3 {:position "absolute"
                    :z-index 12
                    :top (top :line3)
                    :left "0"
                    :background "#fff"
                    :border-radius "3px"
                    :transform (transform :line3)
                    :height "8px"
                    :width "80px"
                    :border "24px #fff"
                    :box-shadow "0 0 13px 0 rgba(0,0,0,0.2)"}]]))
  )

(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "position" 
             :on-click 
             (fn [_]
               (reset! status :three-bar->center))}
       [:div {:class "line1"}]
       [:div {:class "line2"}]
       [:div {:class "line3"}]]]]))

