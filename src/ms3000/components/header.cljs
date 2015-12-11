(ns ms3000.components.header
  (:require
    [om.core :as om :include-macros true]
    [sablono.core :as html :refer-macros [html]]
    [ms3000.router.core :as router]))

(defn component [state owner]
  (om/component
    (html
      [:div
       [:h1 (-> state :title)]
       (router/menu state)])))
