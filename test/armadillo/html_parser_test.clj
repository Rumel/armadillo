(ns armadillo.html-parser-test
  (:require [clojure.test :refer :all]
            [armadillo.html-parser :refer :all]))

(deftest index-ttm-test
  (testing "index-ttm"
    (is 
      (let [ttm-row '(nil nil nil "TTM")]
        (= 3 (index-ttm ttm-row))))
    (is 
      (let [ttm-preliminary '(nil nil "TTMPreliminary" nil)]
        (= 2 (index-ttm ttm-preliminary))))))
