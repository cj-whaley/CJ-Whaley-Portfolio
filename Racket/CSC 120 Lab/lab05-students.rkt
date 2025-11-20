;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-reader.ss" "lang")((modname lab05-students) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; CJ Whaley and Owen Vinson
;; Lab 5 - Modeling Students

;; Data Definitions

(define-struct undergrad (name grad-year major units-comp))
;; an undergrad is (make-undergrad String Number String Number)
;; interp. a student's name, graduation year, major, and number of units completed

(define undergrad1 (make-undergrad "Larry" 2025 "Business" 10))

#;
(define (undergrad-func an-undergrad)
  ( ... (undergrad-name an-undergrad) ...
        ... (undergrad-grad-year an-undergrad) ...
        ... (undergrad-major an-undergrad) ...
        ... (undergrad-units-comp an-undergrad) ...) )


(define-struct graduate (name degree department credits))
;; a graduate is (make-graduate String String String Number)
;; interp. a student's name, degree (MS or PhD), department, and number of credits earned

(define graduate1 (make-graduate "Moe" "PhD" "Math" 100))

#;
(define (graduate-func a-graduate)
  ( ... (graduate-name a-graduate) ...
        ... (graduate-degree a-graduate) ...
        ... (graduate-department a-graduate) ...
        ... (graduate-credits a-graduate) ...) )


(define-struct non-degree (name courses-taken))
;; a non-degree is (make-non-degree String Number)
;; interp. a student's name and number of courses taken

(define non-degree1 (make-non-degree "Curly" 50))

#;
(define (non-degree-func a-non-degree)
  ( ... (non-degree-name a-non-degree) ...
        ... (non-degree-courses-taken a-non-degree) ...) )


;; a Student is one of:
;;   undergrad
;;   graduate
;;   non-degree
;; interp. the type of student

#;
(define (student-func a-student)
   (cond
     [(undergrad? a-student)  ( ... (undergrad-name a-student) ...
                                    ... (undergrad-grad-year a-student) ...
                                    ... (undergrad-major a-student) ...
                                    ... (undergrad-units-comp a-student) ...)]
     [(graduate? a-student) ( ... (graduate-name a-student) ...
                                  ... (graduate-degree a-student) ...
                                  ... (graduate-department a-student) ...
                                  ... (graduate-credits a-student) ...)]
     [(non-degree? a-student) ( ... (non-degree-name a-student) ...
                                    ... (non-degree-courses-taken a-student) ...)  ]
     ) )

;; update-completed : Student Number -> Student
;; takes a student and the number of courses they completed this semester
;;   and produces a student with an updated number of courses

(check-expect (update-completed undergrad1 30) (make-undergrad "Larry" 2025 "Business" 20))
(check-expect (update-completed graduate1 10) (make-graduate "Moe" "PhD" "Math" 130))
(check-expect (update-completed non-degree1 15) (make-non-degree "Curly" 65))

;(define (update-completed a-student courses) a-student)

;(define (update-completed a-student courses)
;   (cond
;     [(undergrad? a-student)  ( ... (undergrad-name an-undergrad) ...
;                                    ... (undergrad-grad-year an-undergrad) ...
;                                    ... (undergrad-major an-undergrad) ...
;                                    ... (undergrad-units-comp an-undergrad) ...)]
;     [(graduate? a-student) ( ... (graduate-name a-graduate) ...
;                                  ... (graduate-degree a-graduate) ...
;                                  ... (graduate-department a-graduate) ...
;                                  ... (graduate-credits a-graduate) ...)]
;     [(non-degree? a-student) ( ... (non-degree-name a-non-degree) ...
;                                    ... (non-degree-courses-taken a-non-degree) ...)  ]
;     ) )

(define (update-completed a-student courses)
   (cond
     [(undergrad? a-student)  (make-undergrad (undergrad-name a-student)
                                     (undergrad-grad-year a-student)
                                     (undergrad-major a-student)
                                     (+ (undergrad-units-comp a-student) (/ courses 3) ))]
     [(graduate? a-student) (make-graduate (graduate-name a-student) 
                                   (graduate-degree a-student) 
                                   (graduate-department a-student) 
                                   (+ (graduate-credits a-student) (* courses 3) ))]
     [(non-degree? a-student) (make-non-degree (non-degree-name a-student)
                                     (+ (non-degree-courses-taken a-student) courses) )  ]
     ) )

;; ready-to-graduate? : Student -> Boolean
;; takes a student and produces whether or not that student is ready to graduate

(check-expect (ready-to-graduate? undergrad1) false)
(check-expect (ready-to-graduate? graduate1) true)
(check-expect (ready-to-graduate? non-degree1) false)

;(define (ready-to-graduate? a-student) true)


;(define (ready-to-graduate? a-student)
;   (cond
;     [(undergrad? a-student)  ( ... (undergrad-units-comp a-student) ...)]
;     [(graduate? a-student) ( ... (graduate-credits a-student) ...)]
;     [(non-degree? a-student)   ]
;     ) )

(define (ready-to-graduate? a-student)
   (cond
     [(and (undergrad? a-student)  (>= (undergrad-units-comp a-student) 15)) true]
     [(graduate? a-student)
      (cond
        [(and (string=? (graduate-degree a-student) "MS") (>= (graduate-credits a-student) 33)) true]
        [(and (string=? (graduate-degree a-student) "PhD") (>= (graduate-credits a-student) 90)) true] ) ]
     [(non-degree? a-student) false ]
     [else false]
     ) )

;; graduate-if-finish-all? : Student Number -> Boolean
;; takes a student and the number of courses they are currently taking and produces whether or not they
;;    will be ready to graduate after completing those courses

(check-expect (graduate-if-finish-all? undergrad1 3) false)
(check-expect (graduate-if-finish-all? undergrad1 6) true)
(check-expect (graduate-if-finish-all? (make-graduate "Moe" "PhD" "Math" 85) 4) false)
(check-expect (graduate-if-finish-all? (make-graduate "Moe" "PhD" "Math" 85) 10) true)
(check-expect (graduate-if-finish-all? non-degree1 10000) false)

;(define (graduate-if-finish-all? a-student courses) true)

;(define (graduate-if-finish-all? a-student courses)
;  (cond
;    [ (undergrad? a-student) ...]
;    [ (graduate? a-student) ...]
;    [ (non-degree? a-student) ...] ) )

(define (graduate-if-finish-all? a-student courses)
  (cond
     [(and (undergrad? a-student)  (>= (+ (undergrad-units-comp a-student) courses ) 15)) true]
     [(graduate? a-student)
      (cond
        [(and (string=? (graduate-degree a-student) "MS") (>= (+ (graduate-credits a-student) courses) 33)) true]
        [(and (string=? (graduate-degree a-student) "PhD") (>= (+ (graduate-credits a-student) courses) 90)) true]
        [else false] ) ] 
     [(non-degree? a-student) false ]
     [else false]
     ) )