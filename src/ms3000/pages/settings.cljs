(ns ms3000.pages.settings
  (:require
    [om.core :as om :include-macros true]
    [ms3000.pages.core :as pages]
    [sablono.core :as html :refer-macros [html]]))

(defn component [state active]
  (let [page "settings"
        route "/settings"]
    [:div {:className (str page " page" (pages/is-active-class route (-> state :router :page)))}
     [:header
      [:h1 page]]]))