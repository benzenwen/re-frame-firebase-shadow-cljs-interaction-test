(ns app.core
  (:require [app.fb.init :refer [firebase-init]]))

(js/console.log "app.core started")

(defn ^:export main []
  (js/console.log "main: before firebase-init, hello world!")
  (firebase-init)
  (js/console.log "main: after firebase-init")
  )
