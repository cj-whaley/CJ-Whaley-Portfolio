;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname lab09-searching-tweets) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; CJ Whaley and Charles Pulaski
;; Lab 09 - Searching Tweets


; A Tweet is a (list String String (listof String)), where
; * The first value is the name of the tweeter, starting with @
; * The second value is message, containing at most 280 characters
; * The third value is a list of hashtags, each starting with #

(define some-tweets
  (list (list "@julia" "I am really excited"
              (list "#bikeride" "#nohomework"))
        (list "@jack" "Frustrating..."
              (list "#notsunny" "#toomuchhomework"))
        (list "@jessica" "What should I do?"
              (list "#nohomework" "#raining"))))

;; matching-messages : [Listof Tweet] String -> [Listof String]
 
(check-expect (matching-messages some-tweets "#nohomework")
              (list "What should I do?" "I am really excited"))
(check-expect (matching-messages some-tweets "#raining")
              (list "What should I do?"))
(check-expect (matching-messages some-tweets "#test")
              empty)


(define (matching-messages tweets hashtag)
 (sort
   ; sort : [List-of String] (String String -> Boolean) -> [List-of String]
  (map second
   ; map : (Tweet -> String) [List-of Tweet] -> [List-of String]
   (filter (λ (tw) (member? hashtag (third tw))) tweets))
   ; filter : (Tweet -> Boolean) [List-of Tweet] -> [List-of Tweet]
  (λ (str1 str2) (< (string-length str1) (string-length str2)))))
  