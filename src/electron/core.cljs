(ns electron.core
  (:require ["electron" :as e :refer [app BrowserWindow]]
            ["path" :as path]
            ["url" :as url]
            ["os" :as os]))

(defonce win-ref (atom nil))

(defn- new-window []
  (let [win (BrowserWindow. #js {:width 800
                                 :height 800
                                 :webPreferences #js {:nodeIntegration true}})
        file-url
        (url/format #js {:pathname (path/join js/__dirname "index.html")
                         :protocol "file:"
                         :slashes true})]

    (.loadURL win file-url)

    (.. win -webContents (openDevTools))

    (reset! win-ref win)

    (.on win "closed" (fn [evt]
                        (reset! win-ref nil)))))

(defn- create-window []
  (when-not @win-ref
    (new-window)))

(defn- quit []
  (when-not (= js/process.platform "darwin")
    (.quit app)))

(defn main []
  (.on app "ready" new-window)
  (.on app "activate" create-window)
  (.on app "window-all-close" quit))

(defn ^:dev/after-load after []
  (main))
