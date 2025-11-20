;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname lab12-rectangle) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; Jacob Bridges, CJ Whaley, & Charles Pulaski
;; Lab 12 - Largest Rectangle

;; largestRectangle : [Listof Number] -> Number

;; takes a list of building heights and returns an integer
;; representing the area of the largest rectangle that can be
;; formed within the bounds of any subset of consecutive buildings

(check-expect (largestRectangle empty)0)
(check-expect (largestRectangle(list 1 2 3 4 5))9)
(check-expect (largestRectangle(list 1 3 5 9 11 5))27)


(define (largestRectangle lon)
  (cond
    [(empty? lon)0]
    [(cons? lon) (Big-Poss (rest (Possibilities lon)) (first (Possibilities lon)))]))
  

;; Possibilities : Listof Numbers -> Listof Numbers
;; Takes a list of numbers and creates all of the possibilites
;; for the given rectangles

(check-expect (Possibilities empty)
                             empty)
(check-expect (Possibilities (list 1 2 3 4 5))
                              (list 5 8 9 8 5))
(check-expect (Possibilities (list 1 3 5 9 11 5))
                             (list 6 15 20 27 22 5))

(define (Possibilities a-lon)
  (cond
    [(empty? a-lon) empty]
    [(cons? a-lon)(cons(*(length a-lon)(first a-lon))
                       (Possibilities (rest a-lon)))]))

;; Big-Poss : Listof Numbers Number -> Numbers
;; Takes a list of Numbers and a base of 0 to
;; check that it is greater than 0 and produces the
;; maximum area the list can produce

(check-expect (Big-Poss (list 5 8 9 8 5)0)9) 
(check-expect (Big-Poss (list 6 15 20 27 22 5)0)27)

(define (Big-Poss a-lon max)
  (cond
    [(empty? a-lon) max]
    [(cons? a-lon) (if (> (first a-lon) max)
                       (Big-Poss (rest a-lon) (first a-lon))
                       (Big-Poss (rest a-lon) max))]))                       


                            

    
  
