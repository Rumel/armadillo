(ns armadillo.utilities-test
  (:require [clojure.test :refer :all]
            [armadillo.utilities :refer :all]))

(deftest convert-number-basic
  (testing "Returns the integer for a number"
    (is (= (convert-number "1000") 1000))))

(deftest convert-number-negative 
  (testing "Returns the integer for a negative number"
    (is (= (convert-number "-1000") -1000))))

(deftest convert-number-with-comma 
  (testing "Returns the integer for a string with a comma"
    (is (= (convert-number "1,000") 1000))))

(deftest convert-number-with-comma-negative
  (testing "Returns the integer for a negative number with a comma"
    (is (= (convert-number "-1,000") -1000))))

(deftest convert-number-with-dot
  (testing "Returns the integer for a number with a dot"
    (is (= (convert-number "0.1") 0.1))))

(deftest convert-number-for-hyphens
  (testing "Returns nil for '--'"
    (is (= (convert-number "--") nil))))

(deftest convert-number-for-hyphens
  (testing "Returns nil for ' '"
    (is (= (convert-number " ") nil))))
