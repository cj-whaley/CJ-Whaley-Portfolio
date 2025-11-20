;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname lab10-kiva) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; CJ Whaley, Jacob Bridges, and Charles Pulaski
;; Lab 10: Real Data: Kiva Microfinance

(require 2htdp/image)
(require 2htdp/universe)
(require sinbad)

;; ===================================================================
;                                                                 
;                                                       ;;        
;   ;;;;            ;                  ;;;;            ;          
;   ;   ;           ;                  ;   ;           ;          
;   ;    ; ;;;;   ;;;;;  ;;;;          ;    ;  ;;;   ;;;;;   ;;;  
;   ;    ;     ;    ;        ;         ;    ; ;;  ;    ;    ;   ; 
;   ;    ;     ;    ;        ;         ;    ; ;   ;;   ;    ;     
;   ;    ;  ;;;;    ;     ;;;;         ;    ; ;;;;;;   ;     ;;;  
;   ;    ; ;   ;    ;    ;   ;         ;    ; ;        ;        ; 
;   ;   ;  ;   ;    ;    ;   ;         ;   ;  ;        ;    ;   ; 
;   ;;;;    ;;;;    ;;;   ;;;;         ;;;;    ;;;;    ;     ;;;  
;                                                                 
;                                                                 
;

(define-struct borrower (name team-sz home activity amt-req amt-rec))
; a borrower is (make-borrower String Number String String Number Number)
; interp. a borrower with a name name, team size borrower-count, home location/country,
;   business type activity, and dollar amounts loan-amount and funded-amount

(define B1 (make-borrower "Tom" 25 "USA" "Farming" 10000 1500))
(define B2 (make-borrower "Fred" 36 "Canada" "Education" 250000 195000))

#;
(define (borrower-func a-borrower)
  (... (borrower-name a-borrower) ...
   ... (borrower-team-sz a-borrower) ...
   ... (borrower-home a-borrower) ...
   ... (borrower-activity a-borrower) ...
   ... (borrower-amt-req a-borrower) ...
   ... (borrower-amt-rec a-borrower) ...))


;; a [Listof Borrower] is either:
;;   empty, or
;;   (cons borrower [Listof Borrower])
;; interp. a list of borrowers

(define LOB1 empty)
(define LOB2 (list B1))
(define LOB3 (list B1 B2))

#;
(define (lob-func a-lob)
  (cond
    [(empty? a-lob) ...]
    [(cons? a-lob) (...  (borrower-func (first a-lob))
                         (lob-func (rest a-lob)) ...)]))





;; ===================================================================
;                                                                 
;                                                                 
;   ;;;;;                         ;       ;                       
;   ;                             ;                               
;   ;      ;   ;  ; ;;    ;;;   ;;;;;   ;;;    ;;;   ; ;;    ;;;  
;   ;      ;   ;  ;;  ;  ;;  ;    ;       ;   ;; ;;  ;;  ;  ;   ; 
;   ;;;;;  ;   ;  ;   ;  ;        ;       ;   ;   ;  ;   ;  ;     
;   ;      ;   ;  ;   ;  ;        ;       ;   ;   ;  ;   ;   ;;;  
;   ;      ;   ;  ;   ;  ;        ;       ;   ;   ;  ;   ;      ; 
;   ;      ;   ;  ;   ;  ;;       ;       ;   ;; ;;  ;   ;  ;   ; 
;   ;       ;;;;  ;   ;   ;;;;    ;;;   ;;;;;  ;;;   ;   ;   ;;;  
;                                                                 
;                                                                 
;                  


;; funds-needed : [Listof Borrower] -> Number
;; consumes a list of borrowers and produces the total amount of money
;;  that these borrowers are still seeking

(check-expect (funds-needed LOB1) 0)
(check-expect (funds-needed LOB2) 8500)
(check-expect (funds-needed LOB3) 63500)

(define (funds-needed a-lob)
  (cond
    [(empty? a-lob) 0]
    [(cons? a-lob) (+ (- (borrower-amt-req (first a-lob))
                         (borrower-amt-rec (first a-lob)))
                      (funds-needed (rest a-lob)))]))

;; find-by-country : String [Listof Borrower] -> [Listof Borrower]
;; consumes a country and a list of borrowers and returns
;;   the list of borrowers from that country

(check-expect (find-by-country "USA" empty) empty)
(check-expect (find-by-country "USA" LOB2) (list B1))
(check-expect (find-by-country "USA" LOB3) (list B1))
(check-expect (find-by-country "Canada" LOB3) (list B2))
(check-expect (find-by-country "Argentina" LOB3) empty)

(define (find-by-country country a-lob)
  (search-borrowers a-lob borrower-home country))


(define (get-kiva-page page-number)
  (local [(define ds (sail-to "https://api.kivaws.org/v1/loans/newest.json"
                              (param "page" page-number)
                              (param "app_id" "edu.berry.cs.sinbad")
                              (cache-timeout (* 24 60 60))  ; refresh downloaded day daily
                              (load)))]
    (fetch ds
           (make-borrower borrower-name borrower-team-sz borrower-home
                              borrower-activity borrower-amt-req borrower-amt-rec)
           (base-path "loans"))))

(define total-kiva-pages
  (fetch (sail-to "https://api.kivaws.org/v1/loans/newest.json"
                  (param "app_id" "edu.berry.cs.sinbad")
                  (cache-timeout (* 24 60 60))  ; refresh downloaded day daily
                  (load))
         (list "paging/pages")))


;; search-borrowers: [Listof Borrowers] (Borrower -> Boolean) -> [Listof Borrowers]
;; takes a list of borrowers and a predicate on borrowers and returns a list
;;   of borrowers where the predicate produces true

(check-expect (search-borrowers empty borrower-name "Tom") empty)
(check-expect (search-borrowers LOB2 borrower-name "Tom") LOB2)
(check-expect (search-borrowers LOB3 borrower-name "Tom") LOB2)
(check-expect (search-borrowers LOB3 borrower-home "Canada") (list B2))
(check-expect (search-borrowers LOB3 borrower-home "China") empty)
(check-expect (search-borrowers LOB3 borrower-amt-req 10000) (list B1))

(define (search-borrowers a-lob borrower-func search)
  (filter (λ (b)
            (if (number? search)
                (= (borrower-func b) search)
                (string=? (borrower-func b) search))) a-lob))


