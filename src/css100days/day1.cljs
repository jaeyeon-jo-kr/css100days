(ns css100days.day1
  (:require [garden.core :refer [css]]
            [reagent.dom :as re-dom]))

(defn styles
  []
  (css [:.frame {:position "absolute"
                 :top "50%"
                 :left "50%"
                 :width "400px"
                 :height "400px"
                 :margin-top "-200px"
                 :margin-left "-200px"
                 :border-radious "2px"
                 :box-shadow "1px 2px 10px 0px rgba(0,0,0,0.3)"
                 :overflow "hidden"
                 :background "-webkit-linear-gradient(bottom left, #43389F 0%, #4ec6ca 100%);"
                 :color "#fff"
                 :font-family "'Courier New', 'Courier', sans-serif"
                 :-webkit-font-smoothing "antialiased"
                 :-moz-osx-font-smoothing "grayscale"}]
       [:.center {:position "absolute"
                  :top "50.8%"
                  :left "50.5%"
                  :transform "translate(-50%,-50%)"}]
       [:.number {:position "relative"
                  :height "100px"
                  :width "200px"}
        [:.one-one {:position "absolute"
                    :z-index "1"
                    :top "0"
                    :left "-16px"
                    :height "40px"
                    :width "20px"
                    :background "#fff"
                    :border-radious "3px"
                    :transform "rotate(50deg)"
                    :box-shadow "0 0 13px 0 rgba(0,0,0,0.2)"}]
        [:.one-two {:position "absolute"
                    :z-index "10"
                    :top "0"
                    :left "0px"
                    :height "100px"
                    :width "24px"
                    :background "#fff"
                    :border-radious "3px"
                    :box-shadow "0 0 13px 0 rgba(0,0,0,0.2)"}]
        [:.zero-one :.zero-two
         {:position "absolute"
          :z-index "8"
          :top "0"
          :left "17px"
          :box-sizing "border-box"
          :height "100px"
          :width "100px"
          :border-radius "50%"
          :border "24px solid #fff"
          :box-shadow "0 0 13px 0 rgba(0,0,0,0.2)"}]
        [:.zero-two
         {:z-index "6"
          :left "100px"}]]
          [:.big {:position "relative"
                  :z-index 20
                  :display "block"
                  :font-size "82px"
                  :line-height "60px"
                  :text-transform "uppercase"
                  :font-weight "700"
                  :margin-top "6px"}]
          [:.big {:position "relative"
                  :z-index 20
                  :display "block"
                  :font-size "82px"
                  :line-height "60px"
                  :text-transform "uppercase"
                  :font-weight "700"
                  :margin-top "6px"}]
          [:.small {:position "relative"
                    :z-index "20"
                    :display "block"
                    :font-size "23px"
                    :line-height "20px"
                    :text-transform "uppercase"
                    :font-weight "700"
                    :letter-spacing ".04em"}]))

(defn framework
  []
  (fn []
    [:div {:class "frame"}
     [:div {:class "center"}
      [:div {:class "number"}
       [:div {:class "one-one"}]
       [:div {:class "one-two"}]
       [:div {:class "zero-one"}]
       [:div {:class "zero-two"}]]
      [:span {:class "big"} "Days"]
      [:span {:class "small"} "CSS Challenge"]]]))
