;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname lab12-change) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;;CJ Whaley, Charles Pulaski, and Jacob Bridges
;;Lab 12

;  change : Number (Listof Number) -> (Listof Number)
;  Compute the number of each coin in 'coins' to be given
;    for the 'amt' of change needed ('amt' is in cents)
(check-expect (change 0  '(25 10 5 1)) '(0 0 0 0))
(check-expect (change 82 '(25 10 5 1)) '(3 0 1 2))
(check-expect (change 60 '(25 10 5 1)) '(2 1 0 0))
(check-expect (change 63 '(25 5 1)) '(2 2 3))
 
(define (change amt coins)
  (cond
    [(empty? coins) empty]
    [(cons? coins) (cons (quotient amt (first coins))
                         (change (remainder amt (first coins)) (rest coins)))]))
