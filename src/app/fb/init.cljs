(ns app.fb.init
  (:require [com.degel.re-frame-firebase :as re-fire] 
            [re-frame.core :as rf]
            [reagent.core :as r]))

;; == firebase-init ===========================================================
;; Initialize default app. Retrieve your own options values by adding a web app
;; on https://console.firebase.google.com
;;
;; usage: (firebase-init))
;;

(defonce firebase-app-info
  {:apiKey "AIzaSyDky8f7d5p8BGoU2XSjLuJ_cXDDJQDlEKs",
   :authDomain "re-frame-firestore-example.firebaseapp.com",
   :databaseURL "https://re-frame-firestore-example.firebaseio.com/",
   :projectId "re-frame-firestore-example",
   :storageBucket "gs://re-frame-firestore-example.appspot.com"})

(rf/reg-event-fx
 :my-empty (fn my-empty [c e other] (js/console.log (str "my-empty keys c = " (keys c) ".  e = " e))))

(rf/reg-event-fx
 :user #(js/console.log (str "got :user event")))

(rf/reg-event-fx
 :set-user #(js/console.log (str "got :set-user event")))

(defonce inited? (r/atom false))
(defn firebase-init
  "Initialize re-frame-firebase (implicitly firebase.js)"
  []
  (when-not @inited?
    (js/console.log "init re-frame-firebase")
    (re-fire/init :firebase-app-info      firebase-app-info
                  :get-user-sub           [:user]
                  :set-user-event         [:set-user]
                  :default-error-handler  [:my-empty :default-error-handler])
    (reset! inited? true)))

