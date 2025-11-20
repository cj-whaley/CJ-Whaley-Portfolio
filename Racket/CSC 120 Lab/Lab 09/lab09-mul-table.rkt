;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname lab09-mul-table) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; CJ Whaley and Charles Pulaski
;; Lab 09 - Multiplication Tables

;; mul-table : [Listof Numbers] -> [Listof [Listof Numbers]]
;; takes a list of numbers and produces a multiplication table for the given list

(check-expect (mul-table empty) empty)
(check-expect (mul-table (list 2 3 5 10))
              (list (list 4 6 10 20)
                    (list 6 9 15 30)
                    (list 10 15 25 50)
                    (list 20 30 50 100)))
(check-expect (mul-table (list 1 3 5 7 9))
              (list (list 1 3 5 7 9)
                    (list 3 9 15 21 27)
                    (list 5 15 25 35 45)
                    (list 7 21 35 49 63)
                    (list 9 27 45 63 81)))


(define (mul-table a-lon)
(local [
        ; row-for-n : Number -> [Listof Number]
        ; produces a list of multiplied values for n
        (define (row-for-n n)
          (map (λ (m) (* m n)) a-lon))
           ; map : (Number -> Number) [List-of Number] -> [List-of Number]
        ]
  (map row-for-n a-lon)))
; map : (Number -> [Listof Number]) [List-of Number] -> [List-of [Listof Number]]