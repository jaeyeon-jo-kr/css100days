(ns css100days.day8)

(def blubbs
  (->> (range 8)
       (map (fn [i] [:div {:class (str "blubb-" i)}]))
       (apply conj [:div {:class "blubbs"}])))

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

