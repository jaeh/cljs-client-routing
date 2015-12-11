(ns ms3000.components.main
  (:require
    [om.core :as om :include-macros true]
    [sablono.core :as html :refer-macros [html]]
    [ms3000.router.core :as router]
    [ms3000.pages.home :as home-page]
    [ms3000.pages.leds :as leds-page]
    [ms3000.pages.settings :as settings-page]))

(def pages [home-page/component
            leds-page/component
            settings-page/component])

(defn component [state]
  (om/component
    (html
      [:div.container
       (for [page pages]
         (page state))])))