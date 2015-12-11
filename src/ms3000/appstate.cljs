(ns ms3000.appstate)

(defonce app-state (atom {:title "MagicShifter3000 UI"
                          :host "magicshifter.local"
                          :port 80
                          :version "0.0.1"
                          :router {:page "/"}
                          :menu [{:href "/" :text "home"}
                                 {:href "/leds" :text "leds"}
                                 {:href "/settings" :text "settings"}]}))