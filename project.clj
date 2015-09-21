(defproject freecoin "0.2.0-SNAPSHOT"
  :description "Freecoin digital currency toolkit"
  :url "http://freecoin.ch"
  :license {:name "GNU GPL Affero v3 and "
            :url "http://www.d-centproject.eu"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [liberator "0.12.2"]
                 [scenic "0.2.5"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-anti-forgery "1.0.0"]
                 [ring/ring-defaults "0.1.5"]
                 [com.cemerick/friend "0.2.1" :exclusions [robert/hooke xml-apis]]
                 [persona-kit "0.1.1-SNAPSHOT" :exclusions [com.cemerick/friend]]
                 [clj-oauth "1.5.1"]
                 [org.slf4j/slf4j-simple "1.7.12"]
                 [org.clojars.d-cent/stonecutter-oauth "0.1.8-SNAPSHOT"]
                 [http-kit "2.1.18"]
                 [enlive "1.1.5"]
                 [formative "0.8.8"]
                 [formidable "0.1.9"]
                 [cheshire "5.4.0"]
                 [json-html "0.2.8"]
                 [autoclave "0.1.7"]
                 [com.novemberain/monger "2.0.0"]
                 [org.clojure/math.numeric-tower "0.0.2"]
                 [com.tiemens/secretshare "1.3.1"]
                 [jstrutz/hashids "1.0.1"]
                 [simple-time "0.2.0"]
                 [environ "1.0.0"]
                 [clojure-humanize "0.1.0"]
                 [clj.qrgen "0.3.0"]
                 [clavatar "0.2.1"]
                 [cc.artifice/lein-gossip "0.2.1"]]
  :source-paths ["src"]
  :jvm-opts ["-Djava.security.egd=file:/dev/random"] ;use a proper random source (install haveged)
  :env [[:base-url "http://localhost:8000"]]
  :aliases {"dev"  ["with-profile" "dev" "ring" "server"]
            "prod" ["with-profile" "production" "run"]}
  :profiles {:dev [:dev-common :dev-local]
             :dev-common {:dependencies [[midje "1.6.3"]
                                         [peridot "0.3.1"]
                                         [kerodon "0.6.1"]]
                          :env [[:base-url "http://localhost:8000"]
                                [:client-id "freecoin"]
                                [:client-secret "freecoin-secret"]
                                [:auth-url "http://localhost:3000"]]
                          :plugins [[lein-midje "3.1.3"]]}
             :production {:source-paths ["src" "prod"]
                          :main freecoin.main}
             :uberjar {:aot :all
                       :main freecoin.main}}
  :plugins [[lein-ring "0.9.3"]
            [lein-environ "1.0.0"]]
  :ring {:reload-paths ["src"]
         :init freecoin.core/lein-ring-init
         :handler freecoin.core/lein-ring-handler
         :destroy freecoin.core/lein-ring-stop
         :port 8000})
