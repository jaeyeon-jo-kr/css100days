(ns css100days.day15
  (:require [garden.core :refer [css]]
            [malli.core :as m]
            [reagent.core :as r]))

(def status-schema 
  [:map 
   [:upload-status [:enum 
                    :ready
                    :file-chosen
                    :uploading
                    :done]]
   [:chosen-file-name [:or nil? string?]]])


(def hidden-style
  [:.hidden
   {:visibility "hidden"}])


(def title
  {:style [:.title
           {:position "relative"
            :top "8%"
            :text-align "center"
            :color "#466a91"}]
   :element [:div {:class "title"}
             "Drop file to upload"]})

(def progress-bar
  {:style [:.progress__bar
           {:position "absolute"
            :border "solid green 2px"
            :top "20%"
            :width "0%"
            :animation "progress 10s forwards"}]
   :element [:div {:class "progress__bar"}]})

(def progress-keyframe
  "@keyframes progress {
	0% {
		width: 0%;
	}
	100% {
		width: 99%;
	}
   }")

(def title-underline-ready
  {:style [:.title__underline__ready
           {:position "absolute"
            :border "solid gray 2px"
            :top "20%"
            :width "99%"}]
   :element [:div {:class "title__underline__ready"}]})

(def title-underline
  {:style [:.title__underline]
   :element-fn
   (fn [status]
     (js/console.debug "title underline : " (:upload-status @status))
     [:div {:class "title__underline"}
      (case (:upload-status @status)
        :ready (:element title-underline-ready)
        :file-chosen (:element title-underline-ready)
        (:element progress-bar))])})

(def file-name
  {:style [:.file__name
           {:position "absolute"}]
   :element-fn
   (fn [status]
     [:p {:class "file__name"}
      (:chosen-file-name @status)])})

(def done
  {:style [:.done__image
           {:position "absolute"
            :top "25%"
            :left "35%"}]
   :element [:img {:class "done__image"
                   :src "asset/exclamation-circle.svg"
                   :width "30%"}]})


(def loading
  {:style [:.file__loading__image
           {:position "absolute"
            :top "25%"
            :left "35%" 
            :animation "loading 2s infinite"}]
   :element [:img {:class "file__loading__image"
                   :src "asset/loading-reload.svg"
                   :width "30%"}]})

(def keyframe-loading
  "@keyframes loading {
	0% {
		transform: rotate(0deg);
	}
	10% {
		opacity: 1;
	}
	90% {
		opacity: 1;
	}
	100% {
		transform: rotate(360deg);
		opacity: 0;
	}
}")

(def file-upload-image 
  {:style [:.file__upload__image
           {:position "absolute"
            :top "20%"
            :left "25%"}]
   :element [:img {:class "file__upload__image"
                   :src "asset/cloud.png"
                   :width "50%"
                   :height "70%"}]})

(defn set-chosen-file-name
  [status info]
  (swap! status assoc 
         
         :chosen-file-name
         (.-name (first info))

         :upload-status
         :file-chosen)
  
  (js/console.debug @status))

(defn open-file-picker
  [status _]
  (-> (js/showOpenFilePicker {:multiple false})
      (.then #(set-chosen-file-name status %))
      (.catch #(js/console.error %))
      (.finally #(js/console.log "File picker closed." %))))

(def file-upload-box
  {:style [:.file__upload__box
           {:position "absolute"
            :top "35%"
            :left "30%"
            :height "25%"
            :width "40%"
            :border "dashed black"}]
   :element-fn (fn [status]
                 [:div {:class "file__upload__box"
                        :on-click (partial open-file-picker status)}
                  (case (:upload-status @status)
                    :ready
                    (:element file-upload-image)
                    :file-chosen
                    ((:element-fn file-name) status)
                    :uploading
                    (:element loading)
                    :done
                    (:element done))
                  #_(:element loading)
                  ])})

(def upload-button-text
  {:style [:.upload__button__text
           {:position "absolute"
            :top "30%"
            :left "25%"
            :color "white"}]
   :element-fn (fn [status]
                 (let [upload-status (:upload-status @status)]
                   (js/console.log upload-status)
                   [:div {:class "upload__button__text"}
                    (case upload-status
                      :ready "Select File"
                      :file-chosen "Upload File"
                      :uploading "Uploading..."
                      :done "Done")]))})

(def upload-button
  {:style [:.upload__button
           {:position "absolute"
            :background "#b3dbbf"
            :top "70%"
            :left "30%"
            :width "40%"
            :height "15%"
            :box-shadow "0px 5px 5px #466a91"}
           [:&:hover
            {:box-shadow "0px 5px 5px #274754"}]]
   :element-fn
   (fn [status]
     [:div {:class "upload__button"
            :on-click (fn [e]
                        (when (= :file-chosen
                                 (:upload-status @status))
                          (js/setTimeout #(swap! status assoc :upload-status :done) 10000)
                          (swap! status assoc :upload-status :uploading)))}
      ((:element-fn upload-button-text) status)])})

(def div-style
  [:div {:display "block"}])


(defn styles
  []
  (->> [[:.framework
         {:position "absolute"
          :top "50%"
          :left "50%"
          :width "400px"
          :height "400px"
          :margin-left "-200px"
          :margin-top "-200px"
          :border "solid blue"
          :border-radius "2px"
          :color "#333"
          :font-family "'Open Sans', Helvetica, sans-serif"}]
        (:style loading)
        (:style title)
        (:style title-underline)
        (:style title-underline-ready)
        (:style upload-button)
        (:style upload-button-text)
        (:style file-upload-box)
        (:style file-upload-image)
        (:style progress-bar)
        (:style done)
        hidden-style
        div-style]
       css
       (str "\n" keyframe-loading "\n")
       (str "\n" progress-keyframe "\n")))


(defn framework
  []
  (let [status (r/atom {:upload-status :ready
                        :chosen-file-name nil})]
    (fn []
      [:div {:class "framework"}
       (:element title)
       ((:element-fn title-underline) status)
       ((:element-fn file-upload-box) status)
       ((:element-fn upload-button) status)])))

