#!/usr/bin/env lein-exec

; Problem 5
;
; 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
; 
; What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?


; slow
;(println
;  (let [divisible-by? (fn [n possible-divisor]
;                        (zero? (mod n possible-divisor)))
;        divisible-by-all-in-range? (fn [n rng]
;                                     (every? #(divisible-by? n %1) rng))
;        possible-numbers (drop 1 (range))
;        target-range (range 1 21)]
;    (first (filter #(divisible-by-all-in-range? %1 target-range)
;                   possible-numbers))))

; fast (adapted from 003)
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
                                  rng-upper-bound #(inc (ceil (/ %1 4)))]
                              (not-any? #(divisible-by? n %1)
                                        (filter odd? (range 2 (rng-upper-bound n)))))))
        all-primes (filter is-prime? (range))
        prime-factors (fn [n]
                        ; JPR TODO: alternate non-loop solution
                        (loop [number n
                               factors {}]
                          (if (= 1 number)
                            factors
                            (let [next-prime-factor (first (filter #(divisible-by? number %1) all-primes))]
                              (recur (/ number next-prime-factor)
                                     (update-in factors [next-prime-factor] (fnil inc 0)))))))
        ; calculate LCM as shown here:
        ; http://www.purplemath.com/modules/lcm_gcf.htm
        prime-factors-of-range (map prime-factors (range 2 21))
        ; Turn:
        ; {2 2, 3 1}
        ; {2 2, 3 2}
        ; Into:
        ; {2 2, 3 2}
        combined-prime-factors (reduce (fn [memo factor-map]
                                         (merge-with max memo factor-map))
                                       prime-factors-of-range)]
    (reduce-kv (fn [memo k v]
                 (* memo (int (Math/pow k v))))
               1 combined-prime-factors)))

