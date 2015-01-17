#!/usr/bin/env lein-exec

; Problem 4
;
; A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
; 
; Find the largest palindrome made from the product of two 3-digit numbers.


(println
  (let [is-palindrome? (fn [n]
                         (let [strn (str n)]
                           (= strn
                              (clojure.string/reverse strn))))
        three-digit-products (for [a (range 100 1000)
                                   b (range 100 1000)]
                               (* a b))]
    (apply max (filter is-palindrome? three-digit-products))))

