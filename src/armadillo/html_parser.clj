(ns armadillo.html-parser
  (:require [clojure.string :as s]
            [net.cgrand.enlive-html :as h]
            [armadillo.utilities :as u]))

(def base-url "http://www.gurufocus.com")

(defn- fetch-url 
  [url]
  (h/html-resource (java.net.URL. url)))

(defn- financial-url
  "Return the url of the stocks 10-Y financials"
  [stock-symbol]
  (str base-url "/financials/" (s/upper-case stock-symbol)))

(defn- financial-page
  [stock-symbol]
  (fetch-url (financial-url stock-symbol)))

(defn- get-table
  [page]
  (h/select page [:table.R10]))

(defn- get-rows
  [table]
  (h/select table [:tr]))

(defn- extract-key
  [row]
  (let [row-text (map h/text (h/select row [:td]))
        k (first row-text)
        v (rest row-text)]
    (if (= k "Fiscal Period") 
      (if (some #(= "TTM" %) v)
        {k v}
        {})
      (if (not (s/blank? k))
        {(s/trim (s/replace k #"\u00A0" " ")) (map u/convert-number v)}
        {}))))

(defn get-stock
  "Return a map of the stock data"
  [stock-symbol]
  (->> (financial-page stock-symbol)
       get-table
       get-rows
       (map extract-key)
       (apply merge)))
