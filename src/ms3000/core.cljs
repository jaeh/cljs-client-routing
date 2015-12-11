(ns ^:figwheel-always ms3000.core
  (:require [om.core :as om :include-macros true]
            [ms3000.appstate :as app]
            [ms3000.components.header :as header]
            [ms3000.components.main :as main]
            [ms3000.debug.core :as debug]
            [sablono.core :as html :refer-macros [html]]
            [ms3000.router.core :as router]
            [ms3000.router.history :as history]))

(enable-console-print!)

(println "Welcome to the MagicShifter 3000 App.")

(om/root header/component app/app-state {:target (. js/document (querySelector "header.main"))})

(om/root main/component app/app-state {:target (. js/document (querySelector "#app"))})

(om/root debug/component app/app-state {:target (. js/document (querySelector "#debug"))})

(defn on-js-reload []
  ;; optionally touch the app-state to force rerendering
  ; (swap! app/app-state update-in [:__figwheel_counter] inc)
  )