#!/usr/bin/env lein-exec

; Problem 7
;
; By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
; 
; What is the 10001st prime number?

(println
  (let [divisible-by? (fn [n possible-divisor]
                        (zero? (mod n possible-divisor)))
        is-prime? (fn [n]
                      (cond (= 1 n) false
                            (= 2 n) true
                            (even? n) false
                            :else 
                            (let [ceil #(int (+ 1 %1))
                                  ; need to add 1 since (range a b) is [a..b)
                                  rng-upper-bound #(inc (ceil (Math/sqrt %1)))]
                              (not-any? #(divisible-by? n %1)
                                        (filter odd? (range 2 (rng-upper-bound n)))))))
        all-primes (filter is-prime? (range))]
    (nth all-primes 10000))) ; 0-based

