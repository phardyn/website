(defproject website "0.1.0-SNAPSHOT"
  :description "Personal portfolio website"
  :url "http://przemyslawhardyn.com"

  :dependencies [[org.clojure/clojure "1.8.0" :scope "provided"]
                 [org.clojure/clojurescript "1.9.229" :scope "provided"]
                 [reagent "0.6.0"]
                 [secretary "1.2.3"]]

  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-figwheel "0.5.4-5"]
            [lein-garden "0.3.0"]]

  :min-lein-version "2.5.0"

  :clean-targets ^{:protect false}
  [:target-path
   [:cljsbuild :builds :app :compiler :output-dir]
   [:cljsbuild :builds :app :compiler :output-to]]

  :resource-paths ["public"]

  :figwheel {:http-server-root "public"
             :nrepl-port 7002
             :nrepl-middleware ["cemerick.piggieback/wrap-cljs-repl"]
             :css-dirs ["public/css"]}

  :garden {:builds [{:id "dev"
                     :source-paths ["src/clj"]
                     :stylesheet website.css/styles
                     :compiler {:output-to "public/css/styles.css"
                                :pretty-print? true}}
                    {:id "production"
                     :source-paths ["src/clj"]
                     :stylesheet website.css/styles
                     :compiler {:output-to "public/css/styles.css"
                                :pretty-print? false}}]}

  :cljsbuild {:builds {:dev
                       {:source-paths ["src/cljs" "env/dev/cljs"]
                        :compiler {:main "website.dev"
                                   :output-to "public/js/app.js"
                                   :output-dir "public/js/out"
                                   :asset-path "/js/out"
                                   :source-map true
                                   :optimizations :none
                                   :pretty-print true}}
                       :production
                       {:source-paths ["src/cljs"]
                        :compiler {:output-to "public/js/app.js"
                                   :optimizations :advanced
                                   :pretty-print false}}}}

  :profiles {:dev {:dependencies [[figwheel-sidecar "0.5.4-5"]
                                  [org.clojure/tools.nrepl "0.2.12"]
                                  [com.cemerick/piggieback "0.2.2-SNAPSHOT"]]}})
