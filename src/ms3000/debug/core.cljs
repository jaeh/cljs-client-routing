(ns ms3000.debug.core
  (:require
    [om.core :as om :include-macros true]
    [sablono.core :as html :refer-macros [html]]))

(defn component [app owner]
  (om/component
    (html
      [:ul
       (for [val app]
         [:li (str val)])])))