#!/usr/bin/env lein-exec

; Problem 3
;
; The prime factors of 13195 are 5, 7, 13 and 29.
; What is the largest prime factor of the number 600851475143?

(println
  (let [divisible-by? (fn [n possible-divisor]
                        (zero? (mod n possible-divisor)))
        is-prime? (fn [n]
                    (cond (= 1 n) false
                          (= 2 n) true
                          (even? n) false
                          :else (not-any? #(divisible-by? n %1)
                                          (filter odd? (range 2 (/ n 4))))))
        all-primes (filter is-prime? (range))
        prime-factors (fn [n]
                        ; JPR TODO: alternate non-loop solution
                        (loop [number n
                               factors []]
                          (if (= 1 number)
                            factors
                            (let [next-prime-factor (first (filter #(divisible-by? number %1) all-primes))]
                              (recur (/ number next-prime-factor)
                                     (conj factors next-prime-factor))))))]
    (apply max (prime-factors 600851475143))))

