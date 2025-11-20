;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname dna) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; CJ Whaley, Jacob Bridges, and Charles Pulaski
;; Lab 12 - DNA Consensus

;; transpose : [Listof [Listof String]] -> [Listof [Listof String]]
;; takes a list of lists of string and creates a list of lists of pairs of strings at the same position in each list

(check-expect (transpose (list (list "a" "b" "c")
                               (list "d" "e" "f")))
              (list (list "a" "d")
                    (list "b" "e")
                    (list "c" "f")))
(check-expect (transpose (list (list "a" "b" "c")
                               (list "d" "e" "f")
                               (list "g" "h" "i")
                               (list "j" "k" "l")))
              (list (list "a" "d" "g" "j")
                    (list "b" "e" "h" "k")
                    (list "c" "f" "i" "l")))
(check-expect (transpose empty) empty)

(define (transpose lols)
  (cond
    [(empty? lols) empty]
    [(cons? lols) (cons (make-pair lols) (transpose (rest lols)))]))


;; make-pair : [Listof [Listof String]] -> [Listof String]
;; takes a list of lists of strings and outputs a list with the first string in each list

(check-expect (make-pair (list (list "a" "b" "c")
                               (list "d" "e" "f")))
              (list "a" "d"))
(check-expect (make-pair (list (list "a" "b" "c")
                               (list "d" "e" "f")
                               (list "g" "h" "i")
                               (list "j" "k" "l")))
              (list "a" "d" "g" "j"))
(check-expect (make-pair empty) empty)

(define (make-pair lols)
  (cond
    [(empty? lols) empty]
    [(cons? lols) (cons (first (first lols))
                        (make-pair (rest lols)))]))


(define test (list (list "a" "b" "c")
                               (list "d" "e" "f")
                               (list "g" "h" "i")
                               (list "j" "k" "l")))