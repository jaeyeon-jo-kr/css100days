(ns css100days.day13
  (:require [garden.core :refer [css]]))

(defprotocol Component
  (style [this]))

(defrecord Overlay []
  Component
  (style [this]
    [:.overlay {:position "absolute"
                :top 0
                :left 0
                :right 0
                :bottom 0
                :background "#000"
                :opacity 0
                :transition "all .6s ease-in-out"}]))

(defrecord Plus []
  Component
  (style [this]
    [:.plus
     {:position "absolute"
      :width "50px"
      :height "50px"
      :top "50%"
      :left "50%"
      :margin "-25px 0 0 -25px"
      :background "#EC6565"
      :border-radius "50%"
      :box-shadow "2px 4px 10px 0 rgba(0,0,0,0.5)"
      :transition "all .4s ease-in-out"
      :opacity 0
      :transform "scale(2)"
      :pointer-events "none"}
     [:&:before
      {:position "absolute"
       :content ""
       :width "14px"
       :height "2px"
       :top "24px"
       :left "18px"
       :background "#fff"}]
     [:&:after
      {:position "absolute"
       :content ""
       :width "2px"
       :height "14px"
       :top "18px"
       :left "24px"
       :background "#fff"}]]))

(defrecord Profile [id image-url]
  Component
  (style [_]
    [:.profile {:position "relative"
                :float "left"
                :width "194px"
                :height "194px"
                :margin "4px 0 0 4px"
                :cursor "pointer"}

     (style (Overlay.))
     (style (Plus.))
     [:&:hover
      [:.plus {:opacity 1
               :transform "scale(1) translate3d(0,0,0)"}]
      [:.overlay {:opacity 0.4}]]])
  )

(defrecord Active []
  Component
  (style [_]
    [:&.active
     {:pointer-events "all"}
     [:.header
      {:transform "translate3d(0,0,0)"
       :transition "all .6s ease-out"}]
     [:.image
      {:transform "translate3d(0,0,0)"
       :transition "all .6s ease-out .3s"}]
     [:.infos {:transform "translate3d(0,0,0)"
               :transition "all .6s ease-out"}]
     [:.close {:transform "rotate(45deg) translate3d(0,0,0)"
               :transition "background .3s ease-in-out, transform .6s ease-out .6s"}]]))


(defrecord Frame []
  Component
  (style [_]
    [:.frame {:position "absolute"
              :top "50%"
              :left "50%"
              :width "400px"
              :height "400px"
              :margin-top "-200px"
              :margin-left "-200px"
              :border-radius "2px"
              :box-shadow "1px 2px 10px 0px rgba(0,0,0,0.3)"
              :background "#fff"
              :color "#fff"
              :font-family "'Open Sans', Helvetica, sans-serif"
              :-webkit-font-smoothing "antialiased"
              :-moz-osx-font-smoothing "grayscale"}]))

(defrecord Close []
  Component 
  (style [_]
    [:.close
     {:transform "rotate(45deg) translate3d(-105%,-105%,0)"
      :transition "background .3s ease-in-out, transform .5s ease-in"
      :top "10px"
      :width "50px"
      :background "$red"
      :cursor "pointer"
      :z-index "2"
      :right "10px"
      :position "absolute"
      :border-radius "50%"
      :height "50px"}
     [:&:before {:position "absolute",
                 :content "''",
                 :width "14px",
                 :height "2px",
                 :top "24px",
                 :left "18px",
                 :background "#fff",
                 :transition "all .3s ease-in-out"}]
     [:&:after {:position "absolute", :content "''",
                :width "2px", :height "14px", :top "18px", :left "24px", :background "#fff",
                :transition "all .3s ease-in-out"}]
     [:&:hover {:background "#fff"}
      [:&:after]
      [:&:before]]]))

(defrecord Detail []
  Component 
  (style [_]
    [:.detail
     {:display "none"
      :position "absolute"
      :z-index "2"
      :top 0
      :left 0
      :right 0
      :bottom 0
      :overflow "hidden"
      :pointer-events "none"
      :font-size 0}
     (style (Active.))]))




(defn styles
  []
  (->> [(style (Frame.))
        (style (Profile. nil nil))
        (style (Detail.))]
       (apply css)))

