(ns css100days.util
  (:require [clojure.string :as string]))

(defn attribute->seq [line]
  (-> (string/split line #"\: ")
      (update 0 keyword)))

(defn attributes->map [lines]
  (->> (string/split lines #";")
       (map (comp attribute->seq string/trim))
       (into {})))

(defn translate-flat-attributes 
  [attributes]
  (->> (string/split attributes #"\{|\}")
       (partition 2)
       (map (fn [[name attribute]]
              [(-> name
                   string/trim
                   keyword)
               (-> attribute
                   string/trim
                   attributes->map)]))))


