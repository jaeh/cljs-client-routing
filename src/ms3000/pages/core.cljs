(ns ms3000.pages.core)

(defn is-active-class [page token]
  (when (= page token)
    " active"))
