(ns armadillo.calculations
  (:require [clojure.math.numeric-tower :as math]
            [clojure.string :as s]
            [clojure.tools.logging :as log]))

(defn- not-nil?
  [object]
  ((complement nil?) object))

(defn- values-for-years
  [stock years row-name]
  (let [this-year (dec (stock :index))
        other-year (- this-year years)
        the-row ((stock :data) row-name)]
    (if (>= other-year 0)
        [(nth the-row other-year) (nth the-row this-year)]
        nil)))

(defn growth-rate
  [[past present] years]
  (if (and (not-nil? present) (not-nil? past) (not-nil? years))
      (if (and (> present 0) (> past 0) (> years 0))
          (try
            (double (- (math/expt (/ present past) (/ 1 years)) 1))
            (catch Exception e (do (log/error e "There was an error in the growth rate calculation") nil)))
          "NEG")
      nil))

(defn all-rates
  [stock row-name]
  (if stock
    (let [nine (values-for-years stock 9 row-name)
          five (values-for-years stock 5 row-name) 
          one (values-for-years stock 1 row-name)]
      [(growth-rate nine 9) (growth-rate five 5) (growth-rate one 1)])
    nil))
