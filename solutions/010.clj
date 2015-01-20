#!/usr/bin/env lein-exec

; Problem 10
;
; The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
; 
; Find the sum of all the primes below two million.

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
    (apply + (take-while #(< %1 2000000) all-primes))))

