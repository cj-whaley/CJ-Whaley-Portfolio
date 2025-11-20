;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname lab12-dna-consensus) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
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
    [(empty? (first lols)) empty]
    [(cons? lols) (cons (make-pair lols) (transpose (map rest lols)))]))


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
    [(empty? (first lols)) empty]
    [(cons? lols) (cons (first (first lols))
                        (make-pair (rest lols)))]))


(define test (list (list "a" "b" "c")
                   (list "d" "e" "f")
                   (list "g" "h" "i")
                   (list "j" "k" "l")))

(define cat "GCATATGGCTGTGCA")
(define dog "GCAAATGGCTGTGCA")
(define horse "GCTAATGGGTGTCCA")
(define cow "GCAAATGGCTGTGCA")
(define monkey "GCAAATCGGTGAGCA")
(define gene-x "GCAAATGGCTGTGCA")

;; consensus : [Listof String] -> String
;; that takes a list of DNA seqeunces (all the same string length)
;;  and produces the consensus string

(check-expect (consensus (list cat dog horse cow monkey)) gene-x)
(check-expect (consensus (list "AACTGT" "CATTGC" "AACGGT" "ACAAGT")) "AACTGT")

(define (consensus a-los)
  (implode
   (map (lambda  (l) (argmax (lambda (str) (count-occurances l str))
                             l))
        (transpose (map explode a-los))))) 

  


;; count-occurances : List-of-Strings String -> Number
;; takes a list of strings and a string and counts the number of times
;; that string appears in the list

(check-expect (count-occurances (list "a" "b" "c") "a") 1)
(check-expect (count-occurances (list "a" "b" "c") "z") 0)
(check-expect (count-occurances (list "a" "b" "c" "b") "b") 2)
(check-expect (count-occurances empty "a") 0)

(define (count-occurances a-los word)
  (cond
    [(empty? a-los) 0]
    [(cons? a-los) (+ (if (string=? word (first a-los)) 1 0)
                      (count-occurances (rest a-los) word))] ) )