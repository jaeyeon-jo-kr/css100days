(ns css100days.day8
  (:require [garden.core :refer [css]]
            [reagent.core :as r]))

(def key-frame-rotate 
  "\n@keyframes rotate {
    0% {
        transform: translate(-50%, -50%) rotate(0deg);
    }
    100% {
        transform: translate(-50%, -50%) rotate(360deg);
    }
}")

(defn blubb-style
  [index]
  [(-> (str ".blubb-" index)
       keyword)
   {:position "absolute"
    :top "20px"
    :left "20px"
    :width "50px"
    :height "50px"
    :background "#fff"
    :border-radius "50%"
    :transform (str "rotate("
                    (rand-int 300)
                    "deg)")
    :transform-origin
    (str
     (-> index (* 3) - (+ 40)) "px "
     (-> index (* 3) - (+ 40)) "px")
    :animation (str "rotate "
                    (-> index
                        (/ 5)
                        (+ 2.5))
                    "s "
                    "ease-in-out "
                    (-> index
                        (/ 5)
                        (+ 2.5))
                    "s "
                    "infinite")
    :filter "blur(5px)"}])

(def blubb-styles
  (->> (range 8)
       (map blubb-style)))

(comment
  (blubb-style 1)
   (blubb-styles))


(defn sparkle-style
  [index]
  [(-> (str ".sparkle-" index)
       keyword)
   {:position "absolute"
    :top "0px"
    :left "0px"
    :width (-> 7
               (+ index)
               (str "px"))
    :height (-> 7
                (+ index)
                (str "px"))
    :background "#fff"
    :border-radius "50%"
    :filter "blur(1px)"
    :transform (str "rotate("
                    (rand-int 300)
                    "deg)")
    :transform-origin (str (-> index
                               (* 2)
                               -
                               (+ 60)) "px "
                           (-> index
                               (* 2)
                               -
                               (+ 60)) "px")
    :animation (str "rotate "
                    (-> index (/ 5) (+ 3.5) (str "s "))
                    "ease-in-out "
                    (-> index (/ 5) (str "s "))
                    "infinite")}])


(def sparkle-styles
  (->> (range 10)
       (map sparkle-style)))

(def ball-style
  [:.ball
   {:position "relative"
    :width "90px"
    :height "90px"
    :background "#fff"
    :border-radius "80%"
    :filter "blur(2px)"}])

(def frame-style
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
            :background "#000000"
            :color "#000000"
            :font-family "'Open Sans', Helvetica, sans-serif"
            :-webkit-font-smoothing "antialiased"
            :-moz-osx-font-smoothing "grayscale"}])

(def center-style
  [:.center {:position "absolute"
             :top "50%"
             :left "50%"
             :transform "translate(-50%,-50%)"}])

(def blubbs
  (->> (range 8)
       (map (fn [i] [:div {:class (str "blubb-" i)}]))
       (apply conj [:div {:class "blubbs"}])))



(defn styles
  []
  (-> (->> [frame-style center-style ball-style]
           (concat sparkle-styles blubb-styles)
           (apply css))
      (str  " " key-frame-rotate)))
(comment 
  (styles)
  )
(def sparkles
  (->> (range 10)
       (map (fn [i] [:div {:class (str "sparkle-" i)}]))
       (apply conj [:div {:class "sparkles"}])))


(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "ball"}]
      sparkles
      blubbs]]))

