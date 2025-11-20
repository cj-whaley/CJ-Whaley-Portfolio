;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname lab12-dragon) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;;CJ Whaley, Charles Pulaski, and Jacob Bridges
;;Lab 12 Dragons

(require 2htdp/universe)
(require 2htdp/image)
;; A Dir is one of the strings:
;; - "up"
;; - "right"
;; - "down"
;; - "left"
;; interp. the direction of the character

;examples
(define dir-1 "up")
(define dir-2 "right")
(define dir-3 "down")
(define dir-4 "left")

#;(define (dir-func a-dir)
  (cond [(string=? a-dir "up") (... a-dir ...)]
        [(string=? a-dir "right") (... a-dir ...)]
        [(string=? a-dir "down") (... a-dir ...)]
        [(string=? a-dir "left") (... a-dir ...)]))




;  rotate-dir : Dir -> Dir
;  Rotate the given direction to the 'left' (counter-clockwise)

(check-expect (rotate-dir dir-1)"left")
(check-expect (rotate-dir dir-2)"up")
(check-expect (rotate-dir dir-3)"right")
(check-expect (rotate-dir dir-4)"down")


(define (rotate-dir a-dir)
  (cond [(string=? a-dir "up")"left"]
        [(string=? a-dir "right")  "up"]
        [(string=? a-dir "down") "right"]
        [(string=? a-dir "left")  "down"]))






;  rotate-dirs : [listof Dir] -> [listof Dir]
;  Rotate all the given Dirs to the 'left' (counter-clockwise)

(check-expect (rotate-dirs empty) empty)
(check-expect (rotate-dirs (list "left" "down"))(list "down" "right"))
(check-expect (rotate-dirs (list "left" "down" "up"))(list "down" "right" "left"))



(define (rotate-dirs a-lod)
  (cond
    [(empty? a-lod)empty]
    [(cons? a-lod)
          (cons(rotate-dir(first a-lod))
                       (rotate-dirs (rest a-lod)))]))







;  move-posn : Posn Dir Number -> Posn
;  Return the adjusted position (as a Posn) of the given Posn
;    moving the given amount in the given direction

(check-expect (move-posn (make-posn 10 20) "up" 10) (make-posn 10 10)) 
(check-expect (move-posn (make-posn 20 0) "up" 10) (make-posn 20 -10))
(check-expect (move-posn (make-posn 50 20) "right" 20) (make-posn 70 20))
(check-expect (move-posn (make-posn 20 30) "left" 35) (make-posn -15 30))
(check-expect (move-posn (make-posn 20 0) "down" 10) (make-posn 20 10))





(define (move-posn posn a-dir amt)
  (cond
     [(string=? a-dir "up")(make-posn (posn-x posn) (- (posn-y posn)amt))] 
        [(string=? a-dir "right")(make-posn(+ (posn-x posn)amt) (posn-y posn))]
        [(string=? a-dir "down")(make-posn (posn-x posn) (+ (posn-y posn)amt))]
        [(string=? a-dir "left")(make-posn(- (posn-x posn)amt) (posn-y posn))]))
    






;  draw-dirs : [listof Dir] Posn Image -> Image
;  Draw lines following the given directions starting at posn into
;    the given scene (any color you choose).


(define (draw-dirs a-lod posn scn)
 (cond
   [(empty? a-lod)scn]
   [(cons? a-lod) (draw-dirs (rest a-lod) (move-posn posn (first a-lod)50) (scene+line scn (posn-x posn)(posn-y posn)
                            (posn-x (move-posn posn (first a-lod) 50))
                            (posn-y (move-posn posn (first a-lod) 50)) "pink"))]))
                           




;  Screen Size...
(define W 400)
(define H 400)
 
 
;  Draw wrapper
(define (draw w)
  (local ((define lst (reverse w)))
    (draw-dirs lst (make-posn (/ W 2) (/ H 2)) (empty-scene W H))))
 
 
;  Key Handler
(define (key w k)
  (cond [(or (key=? k "left")
             (key=? k "right")
             (key=? k "up")
             (key=? k "down"))
         (cons k w)]
        [(key=? k "r") (rotate-dirs w)]
        [else w]))
 





;  jurassic: [listof Dir] Number -> [listof Dir]
;  Compute the next iteration of the Jurassic Fractal, given a [listof Dir]
;    and the number of iterations left.

(check-expect (jurassic (list "up" "right") 0) (list "up" "right"))
(check-expect (jurassic (list "up" "right") 1)(list "up" "right" "up" "left"))
(check-expect (jurassic (list "up" "right") 2)(list "up" "right" "up" "left" "down" "left" "up" "left"))

(define (jurassic a-lod iter)
  (cond
    [(zero? iter)a-lod]
    [else (jurassic (append a-lod (reverse (rotate-dirs a-lod)))  (- iter 1))]))
                   


 
;  A World is a Number
;  interp: number of iterations
(big-bang 0
          (on-key key)
          (on-draw draw))










