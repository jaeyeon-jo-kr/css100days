(ns css100days.day12
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))



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
  [:span {:class "tooltip"
          }
   [:div {:class "info"}
    title-comp
    content-comp]
   "obsession"])


(defn framework
  []
  (fn []
    [:div
     [:div {:class "frame"}
      [:div {:class "center"}
       [:div {:class "quote"} "„"]
       [:p {:class "phrase"}
        "I know quite certainly that I myself have no special talent; curiosity, "
        tooltip-comp
        " and dogged endurance, combined with self-criticism have brought me to my ideas."]
       [:div {:class "writer"}
        "Albert Einstein"]]]]))

