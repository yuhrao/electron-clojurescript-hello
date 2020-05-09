(ns electron.index
  (:require [reagent.core :as r]
            [reagent.dom :as dom]
            [cljs.pprint :as pp]))

(def state (r/atom {:counter 0}))

(defn index []
  [:<>
   [:div.hello "Hello Wolrd!"]
   [:div "My Button was pressed " [:spam.detach (:counter @state)] " times"]
   [:button {:on-click #(swap! state update :counter inc)} "PRESS ME PLEASE!"]])

(defn main []
  (dom/render [index]
              (. js/document querySelector "#app")))

(defn ^:dev/after-load after []
   (main))
