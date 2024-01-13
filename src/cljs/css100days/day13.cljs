(ns css100days.day13
  (:require [garden.core :as g :refer [css]]
            [css100days.util :as u]
            [reagent.core :as r]
            [shadow.json :as json]))
;;;; User Gallery: An idea for a transition between user overview and profile page.

(defprotocol Component
  (element [this]))

(declare Detail)

(defrecord Overlay []
  Component 
  (element [this]))

(defrecord Plus []
  Component 
  (element [this]))

(defrecord Profile [id image-url]
  Component
  (element [_]
    [:div {:class "profile" :id (str "profile-" id)}
     [:img {:src image-url}]
     [:div {:class "overlay"}]
     [:div {:class "plus"}]]))

(def images
  [[1 "https://100dayscss.com/codepen/13-1.jpg"]
   [2 "https://100dayscss.com/codepen/13-2.jpg"]
   [3 "https://100dayscss.com/codepen/13-3.jpg"]
   [4 "https://100dayscss.com/codepen/13-4.jpg"]])


(defrecord Frame []
  Component 
  (element [_]
    [:div {:class "frame"}
     (->> images
          (map (fn [[id url]]
                  (element (Profile. id url))))
          (concat (list (element (Detail.))))
          (apply conj [:div {:class "center"}]))]))

(defrecord Active []
  Component
  (element [_] [:div {:class "active"}]))

(defrecord Close []
  Component
  (element [_]
    [:div {:class "close"}]))


(defrecord Detail []
  Component
  (element [_]
    [:div {:class "detail"}
     (element (Close.))]))

(comment 
  (u/translate-flat-attributes "&:before {
			position: absolute;
			content: '';
			width: 14px;
			height: 2px;
			top: 24px;
			left: 18px;
			background: #fff;
			transition: all .3s ease-in-out;
		}

		&:after {
			position: absolute;
			content: '';
			width: 2px;
			height: 14px;
			top: 18px;
			left: 24px;
			background: #fff;
			transition: all .3s ease-in-out;
		}
		
		&:hover {
			background: #fff;
		}")
  
  "&:after, &:before {
				background: $red;
			}"
  )


(defn framework
  []
  (fn []
    (element (Frame.))))

