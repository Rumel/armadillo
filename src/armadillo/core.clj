(ns armadillo.core
  (:require [clojure.string :as s]
            [armadillo.calculations :as c]
            [armadillo.html-parser :as p]))

(defn format-percentage
  [values]
  (map (fn [value] (if (nil? value)
                       nil
                       (str (format "%.1f" (* value 100)) "%")))
       values))

(defn get-stock
 [string]
 (p/get-stock string))

