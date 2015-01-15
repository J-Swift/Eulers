#!/usr/bin/env lein-exec

; Problem 1
;
; If we list all the natural numbers below 10 that are multiples of 3 or 5, we
; get 3, 5, 6 and 9. The sum of these multiples is 23. Find the sum of all the
; multiples of 3 or 5 below 1000.

(println
  (let [nums (range 1 1000)]
    (reduce (fn [sum current]
              (+ sum (cond (zero? (mod current 3)) current
                           (zero? (mod current 5)) current
                           :else 0)))
            0 nums)))
