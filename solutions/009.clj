#!/usr/bin/env lein-exec

; Problem 9
;
; A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
; a2 + b2 = c2
; 
; For example, 32 + 42 = 9 + 16 = 25 = 52.
; 
; There exists exactly one Pythagorean triplet for which a + b + c = 1000.
; Find the product abc.

(println
  (let [is-pythagorean? (fn [a b c]
                          (and 
                            (< a b c)
                            (= (+ (* a a)
                                  (* b b))
                               (* c c))))
        ; JPR TODO: be smarter
        answer (first (for [a (range 1001)
                            b (range 1001)
                            c (range 1001)
                            :when (and (= 1000
                                          (+ a b c))
                                       (is-pythagorean? a b c))]
                        (vector a b c)))]
    (apply * answer)))

