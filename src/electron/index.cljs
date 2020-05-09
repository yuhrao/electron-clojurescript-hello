(ns electron.index
  (:require [reagent.core :as r]
            [reagent.dom :as dom]))

(def state (r/atom {:counter 0}))

(defn index []
  [:<>
   [:div "Hello Wolrd"]
   [:div "I AM ALIVE! (i think...)"]
   [:div (str "My Button was pressed " (:counter @state) " times")]
   [:button {:on-click #(swap! state update :counter inc)} "PRESS ME PLEASE!"]])

(defn main []
  (dom/render [index]
              (. js/document querySelector "#app")))
