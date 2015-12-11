(ns styles.core
 (:require [garden.def :refer [defstylesheet defstyles]]
           [garden.units :refer [px em]]))

(defstyles headers
 [:h1 :h2 :h3 :h4 :h5 :h6
  {:margin ".5em 0 1em"}]
 [:h1 {:font-size "1.5em"}]
 [:h2 {:font-size "1.3em"}]
 [:h3 {:font-size "1.2em"}]
 [:h4 {:font-size "1.1em"}]
 [:h5 {:font-size "1em"}])

(defstyles typography
 [:body {:font-family "\"Segoe UI\", \"Lucida Grande\", Ubuntu, Verdana, sans-serif"
         :font-size (px 16)}])

(defstyles layout-min-800
 [:header.main {:display "inline-block"
                :width "100%"}
  [:div
   [:h1 {:float "left"
         :max-width "59%"
         :margin 0}]]
  [:nav.main {:float "right"
              :width "40%"
              :text-align "right"}
   [:ul
    [:li {:float "left"
          :padding (str "0 .2em 0")}]]]]
 [:div.container {:margin (str "0 1em")
                  :max-width (px 900)
                  :clear "both"}])

(defstyles screen
 (-> typography)
 (-> headers)
 (-> layout-min-800)
 [:ul {:display "inline-block"
       :margin 0
       :padding 0
       :list-style "none"}
  [:li.col {:float "left"}]
  [:&.leds
   [:li.led {:border "1px solid"
             :height (px 20)
             :width (px 20)}]]]
 [:#debug {:display "none"}]
 [:.page {:position "fixed"
          :left "100%"
          :opacity 0
          :transition "left .5s, opacity .3s"}
  [:&.active {:left 0
              :opacity 1}]])
