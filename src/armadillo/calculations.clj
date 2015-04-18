(ns armadillo.calculations
  (:require [clojure.math.numeric-tower :as math]
            [clojure.string :as s]
            [clojure.tools.logging :as log]))

(def fiscal "Fiscal Period")

(defn- ten-year-values 
  [stock row]
  (let [ttm (stock fiscal)
        the-row (stock row)
        index (.indexOf ttm "TTM")]
    (if (= index 10)
      [(nth the-row 0) (nth the-row 9)]
      nil)))

(defn- five-year-values 
  [stock row]
  (let [ttm (stock fiscal)
        the-row (stock row)
        index (.indexOf ttm "TTM")]
    (if (>= (- index 6) 0)
      [(nth the-row (- index 6) ) (nth the-row (- index 1))]
      nil)))

(defn- one-year-values 
  [stock row]
  (let [ttm (stock fiscal)
        the-row (stock row)
        index (.indexOf ttm "TTM")]
    (if (>= (- index 2) 0)
      [(nth the-row (- index 2) ) (nth the-row (- index 1))]
      nil)))

(defn growth-rate
  [present past years]
  (try
    (double (- (math/expt (/ present past) (/ 1 years)) 1))
    (catch Exception e (do
                         (log/error e "There was an error in the growth rate calculation")
                         nil))))

(defn all-rates
  [stock the-name]
  (if stock
    (let [ten (ten-year-values stock the-name)
          five (five-year-values stock the-name) 
          one (one-year-values stock the-name)]
      [(growth-rate (last ten) (first ten) 10)
      (growth-rate (last five) (first five) 5)
      (growth-rate (last one) (first one) 1)])
    nil))
