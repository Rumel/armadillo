(ns armadillo.calculations-test
  (:require [clojure.test :refer :all]
            [armadillo.calculations :refer :all]
            [clojure.java.io :as io]))

(def google (read-string (slurp (io/file "test/armadillo/data/google.edn"))))
(def facebook (read-string (slurp (io/file "test/armadillo/data/facebook.edn"))))
(def starbucks (read-string (slurp (io/file "test/armadillo/data/starbucks.edn"))))

(deftest not-nil?-test
  (testing "not-nil?"
    (is (= true (#'armadillo.calculations/not-nil? {})))
    (is (= false (#'armadillo.calculations/not-nil? nil)))))

(deftest growth-rate-test 
  (testing "growth rate"
    (is (= 0.2589254117941673 (growth-rate 100 10 10)))
    (is (= nil (growth-rate nil 10 10)))
    (is (= nil (growth-rate 10 nil 10)))
    (is (= nil (growth-rate 10 10 nil)))
    (is (= "NEG" (growth-rate -1 10 10)))
    (is (= "NEG" (growth-rate 100 -1 10)))))
