(ns css100days.day12
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))

(def frame 
  [:.frame {:position "absolute"
            :top "50%"
            :left "50%"
            :width "400px"
            :height "400px"
            :margin-top "-200px"
            :margin-left "-200px"
            :border-radius "2px"
            :box-shadow "4px 8px 16px 0 rgba(0,0,0,0.1)"
            :overflow "hidden"
            :background "#4CB6DE"
            :color "#333"
            :font-family "'Open Sans', Helvetica, sans-serif"
            :-webkit-font-smoothing "antialiased"
            :-moz-osx-font-smoothing "grayscale"}])

(def center
  [:.center {:position "absolute"
             :top "50%"
             :left "50%"
             :transform "translate(-50%,-50%)"}])

(def quote
  [:.quote {:position "absolute"
            :z-index 0
            :top "-340px"
            :left "-160px"
            :color "#6AC2E3"
            :font-size "240px"
            :font-family "Arial"}])

(def phrase 
  [:.phrase {:position "absolute"
             :top "-140px"
             :left "-140px"
             :font-size "24px"
             :width "300px"
             :z-index 1
             :color "white"}])

(def obsession
  [:.obsession
   {:background "#286F8A"}])

(def writer
  [:.writer {:position "absolute"
             :top "100px"
             :left "-140px"
             :color "white"
             :font-size "24px"
             :width "100px"
             :font-style "italic"}])

(def content
  [:.content 
   {:position "absolute"
    :background "#286F8A"
    :color "white"}])

(def emphasize
  [:.emphasize
   {:font-weight "bold"}])

(def tooltip
  [:.tooltip 
   {:position "absolute"
    :width "250px"
    :height "100px"
    :border-radius "1px"
    :border-color "black"}
   content])

(defn styles
  []
  (fn []
    (->> [frame center quote phrase obsession writer tooltip emphasize]
         (apply css))))

(def obsession-comp
  [:span {:class "obsession"
          :on-mouse-over (fn [] (js/console.log "hover"))} "obsession"])

(def word-sound-button-comp
  [:div {:class "sound-button"}])

;;pronunciation
(def word-pron-comp
  [:div {:class "word-pron"}
   "[əbˈseʃ(ə)n]"])


(def title-comp
  [:div {:class "title"}
   word-pron-comp
   word-sound-button-comp])

(def content-comp
  [:div {:class "content"}
   [:span {:class "emphasize"} "Obsession"]
   ", a persistent disturbing preoccupation with an often unreasonable idea or feeling."])

(def tooltip-comp
  [:div {:class "tooltip"}
   title-comp
   content-comp])

(defn framework
  []
  (fn []
    [:div 
     [:div {:class "frame"}
      [:div {:class "center"}
       [:div {:class "quote"} "„"]
       [:p {:class "phrase"}
        "I know quite certainly that I myself have no special talent; curiosity, "
        obsession-comp
        " and dogged endurance, combined with self-criticism have brought me to my ideas."]
       [:div {:class "writer"}
        "Albert Einstein"]]]
     tooltip-comp]
    ))

