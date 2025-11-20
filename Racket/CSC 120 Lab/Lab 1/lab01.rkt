;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname lab01) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; CJ Whaley
;; Lab 01 - Basics
(require 2htdp/image)

;; a blue skyscraper
(above (rectangle 3 20 "solid" "blue")
       (rectangle 15 10 "solid" "blue")
       (rectangle 21 10 "solid" "blue")
       (rectangle 30 110 "solid" "blue")
       )

(define BLDG-WIDTH 50)
(define SPIRE-HGT 10)
(define BLDG-COLOR "red")

;; a red skyscraper
(above (rectangle 3 SPIRE-HGT "solid" BLDG-COLOR)
       (rectangle (* 5/10 BLDG-WIDTH) 10 "solid" BLDG-COLOR)
       (rectangle (* 7/10 BLDG-WIDTH) 10 "solid" BLDG-COLOR)
       (rectangle BLDG-WIDTH 90 "solid" BLDG-COLOR)
       )

;; a grey skyscraper
(above (rectangle 3 20 "solid" "grey")
       (wedge (* 4/10 50) 180 "solid" "grey")
       (rectangle 50 100 "solid" "grey")
       )

;; a group of green triangles
(overlay/align/offset "left" "bottom"
                      (overlay/align/offset "left" "bottom"
                                            (triangle 75 "solid" "green")
                                            50 0
                                            (triangle 95 "solid" "dark green")
                                            )
                      80 0
                      (triangle 120 "solid" "light green")
                      )


;; defining time
(define HOUR 3)
(define MINUTE 16)
(define SECOND 5)

(define FRAME (bitmap/url "https://cs.berry.edu/webdocs-common/csc120/2024spring/labs/lab01/phone-frame.png"))

(define BKG (empty-scene 250 445 "blue"))

(define TIME (string-append
              (if (< HOUR 10) "0" "")
              (number->string HOUR)
              ":"
              (if (< MINUTE 10) "0" "")
              (number->string MINUTE)
              ":"
              (if (< SECOND 10) "0" "")
              (number->string SECOND)))

(define SCREEN (place-image
                (text TIME 48 "white")
                (/ (image-width BKG) 2)
                (/ (image-height BKG) 2)
                BKG
                ))

(place-image
 SCREEN
 (/ (image-width FRAME) 2)
 (/ (image-height FRAME) 2)
 FRAME
 )


;;=====================
;;Lab 01 - Homework Tasks
;; Magenta Skyscraper

(above (rectangle 2 25 "solid" "magenta")
       (overlay/align/offset "middle" "bottom"
       (rectangle 40 10 "solid" "magenta")
       0 0
       (ellipse 18 25 "solid" "magenta")
       )
       (rectangle 50 100 "solid" "magenta")
       )

;; Building Silhouette

(define BLD-BKG (empty-scene 200 200 "white"))
(place-image
(overlay/offset 
(above (rectangle 2 25 "solid" "magenta")
       (overlay/align/offset "middle" "bottom"
       (rectangle 40 10 "solid" "magenta")
       0 0
       (ellipse 18 25 "solid" "magenta")
       )
       (rectangle 50 100 "solid" "magenta")
       )
5 0
(above (rectangle 2 25 "solid" "black")
       (overlay/align/offset "middle" "bottom"
       (rectangle 40 10 "solid" "black")
       0 0
       (ellipse 18 25 "solid" "black")
       )
       (rectangle 50 100 "solid" "black")
       ))
(/ (image-width BLD-BKG) 2)
(+ (/ (image-height BLD-BKG) 2) 25)
BLD-BKG)

;; Jalopy

(define WHEEL
(overlay
(circle 5 "outline" "black")
(circle 5 "solid" "grey")))

(overlay/align/offset "right" "bottom"
                      WHEEL
                      0 0
                      (overlay/align/offset "left" "bottom"
                                            WHEEL
                                            0 -10
                                            (above
                                             (beside
                                              (flip-horizontal (right-triangle 10 10 "solid" "orange"))
                                              (square 10 "solid" "orange")
                                              (right-triangle 10 10 "solid" "orange"))
                                             (rectangle 40 10 "solid" "orange"))))

;; Clock Screen Redesign
(define HOUR2 18)
(define MINUTE2 16)


;;(define FRAME (bitmap/url "https://cs.berry.edu/webdocs-common/csc120/2024spring/labs/lab01/phone-frame.png"))

(define COLOR (if (and (> HOUR2 7) (< HOUR2 19)) "light orange" "navy"))
(define BKG2 (empty-scene 250 445 COLOR))

(define TIME2 (string-append
              (if (< HOUR2 10) "0" "")
              (if (> HOUR2 12) (number->string (- HOUR2 12)) (number->string HOUR2))
              ":"
              (if (< MINUTE 10) "0" "")
              (number->string MINUTE2)
              ))

(define SCREEN2 (place-image
                (text TIME2 48 "white")
                (/ (image-width BKG2) 2)
                (/ (image-height BKG2) 4)
                BKG2
                ))

(place-image
 SCREEN2
 (/ (image-width FRAME) 2)
 (/ (image-height FRAME) 2)
 FRAME
 )


;;===================
;; Extra Credit

(define EAT "EAT")
;;(circle 50 "solid" "red")
;;(square 50 "solid" "dark Green")

(define DIAMOND-EAT (overlay (text EAT 45 "dark green")
                             (circle 50 "solid" "red")
                             (rotate 45 (square 100 "solid" "dark Green"))))
                             