(ns armadillo.utilities
  (:require [clojure.string :as s]))

(defn convert-number
  [number-string]
  (let [without-comma (s/replace number-string "," "")
        trimmed (s/trim without-comma)]
    (try
      (Integer/parseInt trimmed)
      (catch Exception e (try
                           (Double/parseDouble trimmed)
                           (catch Exception e nil)))))) 
