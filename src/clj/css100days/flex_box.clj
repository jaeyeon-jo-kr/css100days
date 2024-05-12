(ns css100days.flex-box
  (:require [garden.core :refer [css]]))

(def ^:style flex-1
  [:.flex-1 {:margin "10px"
             :background-color "rgba(255, 0, 200, 0.2)"
             :flex-grow 1}])

(def ^:style flex-1-30px
  [:.flex-1-30px {:margin "10px"
                  :background-color "rgba(255, 0, 200, 0.2)"
                  :flex-grow 1
                  :flex-basis "30px"}])

(def ^:style flex-1-1-100px
  [:.flex-1-1-100px {:margin "10px"
                     :background-color "rgba(255, 0, 200, 0.2)"
                     :flex-grow 1
                     :flex-shrink 1
                     :flex-basis "100px"}])


(def ^:style other-flex-1
  [:.other-flex-1 {:margin "10px"
                   :background-color "rgba(155, 0, 100, 0.2)"
                   :flex 1}])

(def ^:style flex-2
  [:.flex-2 {:margin "10px"
             :background-color "rgba(255, 0, 200, 2)"
             :flex 1}])

(def ^:style example-1
  [:.example-1 {:display "flex"}])

(def ^:style example-2
  [:.example-1 {:display "flex"
                :flex-direction ""}])

(defn styles
  []
  (css [:* {:box-sizing "border-box"}]
       flex-1
       flex-2
       other-flex-1
       flex-1-30px
       flex-1-1-100px
       example-1))