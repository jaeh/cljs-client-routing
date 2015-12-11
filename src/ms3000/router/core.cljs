(ns ms3000.router.core
  (:require
    [om.core :as om :include-macros true]
    [sablono.core :as html :refer-macros [html]]
    [ms3000.router.history :as history]
    [goog.events])
  (:import
    [goog History]
    [goog.history Html5History EventType]))

(defn link [state item]
  (let [href (-> item :href)
        text (-> item :text)]
    [:li
     [:a {:href href
          :onClick #(do
                      (.preventDefault %)
                      (history/nav! state href))}
      text]]))

(defn menu [state]
  (let [items (-> state :menu)]
    (html
      [:nav.main
       [:ul
        (for [item items]
          (link state item))]])))
