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


(comment
  (attributes->map "position: absolute;
			content: '';
			width: 14px;
			height: 2px;
			top: 24px;
			left: 18px;
			background: #fff;
			transition: all .3s ease-in-out;")
  (string/split "&:before {position: absolute;}"
                #"\{|\}")
  
  (translate-flat-attributes
   "&:before {
			position: absolute;
			content: '';
			width: 14px;
			height: 2px;
			top: 24px;
			left: 18px;
			background: #fff;
			transition: all .3s ease-in-out;
		}
")

"&:before {
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
			
			&:after, &:before {
				background: $red;
			}
		}"

  (translate-attribute-content "position: absolute;
		z-index: 2;")

  (translate-line "position: absolute")

  (translate-attribute-content "position: absolute;
		z-index: 2;
		width: 50px;
		height: 50px;
		top: 10px;
		right: 10px;
		background: $red;
		border-radius: 50%;
		transition: background .3s ease-in-out, transform .5s ease-in;
		transform: rotate(45deg) translate3d(-105%,-105%,0);
		cursor: pointer;")
  (translate-css-element )

  
  )