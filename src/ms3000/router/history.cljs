(ns ms3000.router.history
  (:require
    [om.core :as om :include-macros true]
    [goog.events])
  (:import
    [goog History]
    [goog.history Html5History EventType]))

;; Replace this method:
;;  https://closure-library.googlecode.com/git-history/docs/local_closure_goog_history_html5history.js.source.html#line237
(aset js/goog.history.Html5History.prototype "getUrl_"
  (fn [token]
    (this-as this
      (if (.-useFragment_ this)
        (str "#" token)
        (str (.-pathPrefix_ this) token)))))

(defn get-token []
  (let [pathname (str js/window.location.pathname)
        search (str js/window.location.search)]
    (if (Html5History.isSupported)
      (str pathname search)
      (if (= pathname "/")
        (.substring js/window.location.hash 1)
        (str pathname search)))))

(defn handle-url-change [e]
  (let [token (get-token)
        protocol (str js/window.location.protocol)
        host (str js/window.location.host)
        pathname (str js/window.location.pathname)]
    (when-not (.-isNavigation e)
      ;; this is where state transitions happens
      (js/console.log (str "Navigate to: " token)))))

(defn make-history []
  (let [protocol (str js/window.location.protocol)
        host (str js/window.location.host)
        pathname (str js/window.location.pathname)]
    (if (Html5History.isSupported)
      (let [location (str protocol "//" host)]
        (doto (Html5History.)
          (.setPathPrefix (str protocol "//" host))
          (.setUseFragment false)))
      (if (not= "/" pathname)
        (aset js/window "location" (str "/#" (get-token)))
        (History.)))))

(defonce history (doto (make-history)
                   (goog.events/listen EventType.NAVIGATE
                     ;; wrap in a fn to allow live reloading
                     #(handle-url-change %))
                   (.setEnabled true)))

(defn nav! [state token]
  (let [page (-> state :router :page)]
    (when (not= page token)
      (om/update! state [:router :page] token)
      (.setToken history token))))