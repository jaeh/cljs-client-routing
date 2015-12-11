(defproject ms3000 "0.1.0-SNAPSHOT"
  :description "A prototype for the MagicShifter3000 UserInterface, written in ClojureScript"
  :url "http://github.com/magicshifter/onboard-ui"
  :license {:name "AGPL v3.0"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.145"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [sablono "0.3.6"]
                 [org.omcljs/om "0.9.0"]
                 [com.stuartsierra/component "0.3.0"]
                 [ring-jetty-component "0.3.0"]
                 [compojure "1.4.0"]
                 [garden "1.3.0-SNAPSHOT"]
                 [figwheel-sidecar "0.4.1"]]

  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-figwheel "0.4.1"]
            [lein-ring "0.9.7"]
            [lein-garden "0.2.6"]
            [hiccup-watch "0.1.1"]]

  :source-paths ["src"]

  :ring {:handler server.core/app
         :port 8000}

  :clean-targets ^{:protect false} ["resources"
                                    "target"]

  :cljsbuild { :builds [ {:id "dev"
                          :source-paths ["src"]
                          :figwheel {:on-jsload "ms3000.core/on-js-reload"}

                          :compiler {:main ms3000.core
                                     :asset-path "js/compiled/out"
                                     :output-to "resources/public/js/main.js"
                                     :output-dir "resources/public/js/compiled/out"
                                     :source-map-timestamp true}}
                         {:id "min"
                          :source-paths ["src"]

                          :compiler {:output-to "resources/public/js/main.min.js"
                                     :main ms3000.core
                                     :optimizations :advanced
                                     :pretty-print false}}]}

  :uberjar-name "server.jar"

  :garden {:builds [{:id "dev"
                     ;; Source paths where the stylesheet source code is
                     :source-paths ["src/styles"]
                     ;; The var containing your stylesheet:
                     :stylesheet styles.core/screen
                     ;; Compiler flags passed to `garden.core/css`:
                     :compiler {;; Where to save the file:
                                :output-to "resources/public/css/screen.css"
                                ;; Compress the output?
                                :pretty-print? true}}
                    {:id "min"
                     :source-paths ["src/styles"]
                     :stylesheet styles.core/screen
                     :compiler {
                                 :output-to "resources/public/css/screen.css"
                                 :pretty-print? false}}]}

  :hiccup-watch {:input-dir "src/html"
                 :output-dir "resources/public"}

  :figwheel-options {:http-server-root "public" ;; default and assumes "resources"
                     :server-port 3449 ;; default
                     :server-ip "127.0.0.1"

                     :css-dirs ["resources/public/css"] ;; watch and update CSS

                     ;; Start an nREPL server into the running figwheel process
                     :nrepl-port 7888})
