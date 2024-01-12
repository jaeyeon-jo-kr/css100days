(ns css100days.selector
  (:require [garden.core :as g :refer [css]]))


(def id-selector
  {:.style [:#id__selector
            {:color "red"}]
   :element [:p {:id "id__selector"}
             "ID Selector"]})

(def framework 
  {:style [:.framework
           {:position "absolute"
            :top "50%"
            :left "50%"
            :width "400px"
            :height "400px"
            :margin-left "-200px"
            :margin-top "-200px"
            :border "solid blue"
            :border-radius "2px"
            :color "#333"
            :font-family "'Open Sans', Helvetica, sans-serif"}]
   :element [:div {:class "framework"}]})

(defn styles
  []
  (->> [framework id-selector]
       (map :style)
       (apply css)))

(defn view
  []
  (fn []
    (:element framework)))

(comment 
  (defn macro-helper [a] (print a)
    a)
  (defmacro abc [a b]
    `{:styles ~(macro-helper a)
      :element ~b}
    )
  (def result (abc [:.a] (fn [])))
  result
  
  )
