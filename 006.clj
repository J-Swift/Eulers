#!/usr/bin/env lein-exec

; Problem 6
;
; The sum of the squares of the first ten natural numbers is,
; 1^2 + 2^2 + ... + 10^2 = 385
; 
; The square of the sum of the first ten natural numbers is,
; (1 + 2 + ... + 10)^2 = 55^2 = 3025
; 
; Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
; 
; Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.


(println
  (let [sum-of-squares (fn [rng]
                         (apply + (map #(* %1 %1) rng)))
        square-of-sum (fn [rng]
                        (let [sum (apply + rng)]
                          (* sum sum)))
        target-range (range 1 101)]
    (- (square-of-sum target-range)
       (sum-of-squares target-range))))

