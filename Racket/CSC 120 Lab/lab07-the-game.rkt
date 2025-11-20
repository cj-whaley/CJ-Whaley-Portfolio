;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-abbr-reader.ss" "lang")((modname lab07-the-game) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; CJ Whaley and Owen Vinson
;; Lab 07: Racman, The Game, Version 1

(require 2htdp/image)
(require 2htdp/universe)
(require sinbad)

;; ===================================================================
;                                                                 
;                                                                 
;     ;;;                         ;                    ;          
;    ;   ;                        ;                    ;          
;   ;       ;;;   ; ;;    ;;;   ;;;;;  ;;;;   ; ;;   ;;;;;   ;;;  
;   ;      ;; ;;  ;;  ;  ;   ;    ;        ;  ;;  ;    ;    ;   ; 
;   ;      ;   ;  ;   ;  ;        ;        ;  ;   ;    ;    ;     
;   ;      ;   ;  ;   ;   ;;;     ;     ;;;;  ;   ;    ;     ;;;  
;   ;      ;   ;  ;   ;      ;    ;    ;   ;  ;   ;    ;        ; 
;    ;   ; ;; ;;  ;   ;  ;   ;    ;    ;   ;  ;   ;    ;    ;   ; 
;     ;;;   ;;;   ;   ;   ;;;     ;;;   ;;;;  ;   ;    ;;;   ;;;  
;                                                                 
;                                                                 
;


(define CELL-SIZE 20)
(define CELLS-WID 30)
(define CELLS-HIGH 20)


(define TOTAL-WID (* CELLS-WID CELL-SIZE))
(define TOTAL-HIGH (* CELLS-HIGH CELL-SIZE))
(define DELAY 6)
(define BACKGROUND (overlay (empty-scene TOTAL-WID TOTAL-HIGH "black")
                            (empty-scene (+ TOTAL-WID CELL-SIZE) (+ TOTAL-HIGH CELL-SIZE) "blue")))

;; new constants
(define EYES-CENTER
  (overlay/align "middle" "middle"
                 (circle 1 "solid" "black")
                 (circle 3 "solid" "white")))

(define EYES-LEFT
  (overlay/align "left" "middle"
                 (circle 1 "solid" "black")
                 (circle 3 "solid" "white")))

(define EYES-RIGHT
  (overlay/align "right" "middle"
                 (circle 1 "solid" "black")
                 (circle 3 "solid" "white")))

(define GHOST-BODY
  (overlay/align "middle" "bottom"
                 (beside (triangle 6 "solid" "black")
                         (triangle 5 "solid" "black")
                         (triangle 6 "solid" "black"))
                 (rectangle 20 10 "solid" "red")
                 (circle 10 "solid" "red")))

(define GHOST-CENTER
  (overlay (beside EYES-CENTER (rectangle 2 0 "solid" "red") EYES-CENTER)
           GHOST-BODY))

(define GHOST-LEFT
  (overlay (beside EYES-LEFT (rectangle 2 0 "solid" "red") EYES-LEFT)
           GHOST-BODY))

(define GHOST-RIGHT
  (overlay (beside EYES-RIGHT (rectangle 2 0 "solid" "red") EYES-RIGHT)
           GHOST-BODY))

(define WALL (square 20 "solid" "blue"))

(define FOOD (overlay (circle 3 "solid" "green")
                      (square CELL-SIZE "solid" "black")))


(define WALLS (fetch (sail-to "map.csv" (load)) (make-posn "x" "y")))
(define FOODS (fetch (sail-to "food.csv" (load)) (make-posn "x" "y")))


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


;; Dir is one of
;; - "N"
;; - "E"
;; - "S"
;; - "W"
;; interp. the heading of the character

#;
(define (dir-func a-dir)
  (cond
    [(string=? a-dir "N") ...]
    [(string=? a-dir "E") ...]
    [(string=? a-dir "S") ...]
    [(string=? a-dir "W") ...] ) )




;; A Posn is (make-posn Number Number)
;; interp. represents an (x, y) location in game scene

(define POSN1 (make-posn 40 40))
(define POSN2 (make-posn 60 80))

#;
(define (posn-func a-posn)
  (... (posn-x a-posn) ...
   ... (posn-y a-posn) ...))    



;; List-of-Posns is one of:
;;  - empty
;;  - (cons Posn List-of-Posns)
;;  - interp. a list of Posns

(define LOP1 empty)
(define LOP2 (cons POSN1 empty))
(define LOP3 (cons (make-posn 120 40) (cons POSN2 empty)))

#;
(define (lop-func a-lop)
  (cond
    [(empty? a-lop) (...)]
    [(cons? a-lop) (... (posn-func (first a-lop) )...
                    ... (lop-func (rest a-lop)) ...)]))




(define-struct racman (position direction mouth wait))
;; a racman is (make-racman (Posn Dir Number Number))

;; Racmen
(define rac1 (make-racman (make-posn 20 20) "E" 50 6))
(define rac2 (make-racman (make-posn 20 60) "S" 30 0))
(define rac3 (make-racman (make-posn 120 120) "N" 20 0))
(define rac4 (make-racman (make-posn 400 400) "N" 20 6))
(define rac5 (make-racman (make-posn 620 400) "N" 20 6))
(define rac6 (make-racman (make-posn 600 380) "N" 20 6))


#;
(define (racman-func a-racman)
  (... (posn-func (racman-position a-racman)) ...
   ... (dir-func (racman-direction a-racman)) ...
   ... (racman-mouth a-racman) ...
   ... (racman-wait a-racman) ...))



(define-struct ghost (position direction life wait))
;; a ghost is (make-ghost Posn Dir Number Number)

(define GHOST1 (make-ghost (make-posn 80 80) "E" 55 6))
(define GHOST2 (make-ghost (make-posn 140 100) "S" 74 0))
(define GHOST3 (make-ghost (make-posn 100 100) "W" 74 0))

#;
(define (ghost-func a-ghost)
  (... (posn-func (ghost-position a-ghost)) ...
   ... (dir-func (ghost-direction a-ghost)) ...
   ... (ghost-life a-ghost) ...
   ... (ghost-wait a-ghost) ...))



;; List-of-Ghosts is one of:
;;  - empty
;;  - (cons ghost List-of-Ghosts)
;;  - interp. a list of ghosts

(define LOG1 empty)
(define LOG2 (cons GHOST1 empty))
(define LOG3 (cons (make-ghost (make-posn 40 120) "S" 22 0)
                   (cons GHOST1 empty)))

#;
(define (log-func a-log)
  (cond
    [(empty? a-log) (...)]
    [(cons? a-log) (... (ghost-func (first a-log) )...
                    ... (log-func (rest a-log)) ...)]))    



(define-struct game (racman walls ghosts foods))
;; game is (make-game racman List-of-Posns List-of-Ghosts List-of-Posns)

(define GAME1 (make-game rac1 LOP2 LOG3 LOP3))
(define GAME2 (make-game
               (make-racman (make-posn 100 100) "N" 30 6)
               (cons (make-posn 20 40) (cons (make-posn 20 60) empty))
               (cons (make-ghost (make-posn 100 100) "W" 60 0) empty)
               (cons (make-posn 60 60) (cons (make-posn 180 100) empty)) ) )
(define GAME3 (make-game
               (make-racman (make-posn 60 60) "N" 30 6)
               (cons (make-posn 20 40) (cons (make-posn 20 60) empty))
               (cons (make-ghost (make-posn 180 120) "W" 60 0) empty)
               empty))
(define GAME4 (make-game
               rac1
               (list (make-posn 40 80) (make-posn 60 80) (make-posn 80 60))
               (list GHOST2 GHOST3)
               (list (make-posn 20 20) (make-posn 20 40) (make-posn 20 60) (make-posn 40 20) (make-posn 60 20)
                     (make-posn 100 100) (make-posn 100 120) (make-posn 120 120) (make-posn 120 100) (make-posn 380 280))))

#;
(define (game-func a-game)
  (... (racman-func (game-racman a-game) )...
   ... (lop-func (game-walls a-game) )...
   ... (log-func (game-ghosts a-game) )...
   ... (lop-func (game-foods a-game) )...))
                 


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


;; racman-image : Number Dir -> Image
; takes a mouth opening size and a direction and
; produces an appropriate image of a RacMan character.
(check-expect (racman-image 50 "N") (overlay/align "middle" "top"
                                                   (rotate 180 (isosceles-triangle (/ CELL-SIZE 2) 50 "solid" "black"))
                                                   (circle (/ CELL-SIZE 2) "solid" "yellow")))
(check-expect (racman-image 25 "W") (overlay/align "left" "middle"
                                                   (rotate -90 (isosceles-triangle (/ CELL-SIZE 2) 25 "solid" "black"))
                                                   (circle (/ CELL-SIZE 2) "solid" "yellow")))
(check-expect (racman-image 45 "E") (overlay/align "right" "middle"
                                                   (rotate 90 (isosceles-triangle (/ CELL-SIZE 2) 45 "solid" "black"))
                                                   (circle (/ CELL-SIZE 2) "solid" "yellow")))
(check-expect (racman-image -35 "S") (overlay/align "middle" "bottom"
                                                    (isosceles-triangle (/ CELL-SIZE 2) (abs -35) "solid" "black")
                                                    (circle (/ CELL-SIZE 2) "solid" "yellow")))

(define (racman-image mouth direction)
  (cond [(string=? direction "N") (overlay/align "middle" "top"
                                                 (rotate 180 (isosceles-triangle (/ CELL-SIZE 2) (abs mouth) "solid" "black"))
                                                 (circle (/ CELL-SIZE 2) "solid" "yellow"))]
        [(string=? direction "S") (overlay/align "middle" "bottom"
                                                 (isosceles-triangle (/ CELL-SIZE 2) (abs mouth) "solid" "black")
                                                 (circle (/ CELL-SIZE 2) "solid" "yellow"))]
        [(string=? direction "E") (overlay/align "right" "middle"
                                                 (rotate 90 (isosceles-triangle (/ CELL-SIZE 2) (abs mouth) "solid" "black"))
                                                 (circle (/ CELL-SIZE 2) "solid" "yellow"))]
        [(string=? direction "W") (overlay/align "left" "middle"
                                                 (rotate -90 (isosceles-triangle (/ CELL-SIZE 2) (abs mouth) "solid" "black"))
                                                 (circle (/ CELL-SIZE 2) "solid" "yellow"))]))



;; render-racman : Racman Image -> Image
; produces an image of the current state of the game

(check-expect (render-racman rac1 BACKGROUND) (place-image (racman-image 50 "E") 20 20 BACKGROUND))
(check-expect (render-racman rac2 BACKGROUND) (place-image (racman-image 30 "S") 20 60 BACKGROUND)) 

(define (render-racman a-racman bkg)
  (place-image
   (racman-image (racman-mouth a-racman)(racman-direction a-racman))
   (posn-x (racman-position a-racman))
   (posn-y (racman-position a-racman))
   bkg))



;; render-walls : List-of-posns Image -> Image
;; places the WALL image at every posn in the given list on the given background

(check-expect (render-walls empty BACKGROUND) BACKGROUND)

(check-expect (render-walls (cons (make-posn 60 20) (cons (make-posn 80 40) empty))
                            BACKGROUND)
              (place-image WALL 60 20 (place-image WALL 80 40 BACKGROUND)))
(check-expect (render-walls (cons (make-posn 100 100)
                                  (cons (make-posn 60 20) (cons (make-posn 80 40) empty)))
                            BACKGROUND)
              (place-image WALL 100 100
                           (place-image WALL 60 20 (place-image WALL 80 40 BACKGROUND))))

(define (render-walls a-lop bkg)
  (cond
    [(empty? a-lop) bkg]
    [(cons? a-lop) (render-wall (first a-lop) 
                                (render-walls (rest a-lop) bkg))]))


;; render-wall : Posn Image -> Image

(check-expect (render-wall (make-posn 240 120) BACKGROUND) (place-image WALL 240 120 BACKGROUND))
(check-expect (render-wall (make-posn 100 200) BACKGROUND) (place-image WALL 100 200 BACKGROUND))

(define (render-wall a-posn bkg)
  (place-image WALL (posn-x a-posn) (posn-y a-posn) bkg))



;; render-ghosts : List-of-Ghosts Image -> Image
;; places the GHOST-BODY image at every posn in the given list on the given background

(check-expect (render-ghosts empty BACKGROUND) BACKGROUND)
 
(check-expect (render-ghosts LOG2
                            BACKGROUND) 
              (render-ghost GHOST1 BACKGROUND))
(check-expect (render-ghosts LOG3
                            BACKGROUND)
              (render-ghost (make-ghost (make-posn 40 120) "S" 22 0)
                           (render-ghost GHOST1 BACKGROUND)))
 

(define (render-ghosts a-log bkg)
  (cond
    [(empty? a-log) bkg]
    [(cons? a-log) (render-ghost (first a-log) 
                                (render-ghosts (rest a-log) bkg))]))


;; ghost-image : Dir -> Image
; takes a direction and
; produces an appropriate image of a ghost.
(check-expect (ghost-image "N") GHOST-CENTER)
(check-expect (ghost-image "W") GHOST-LEFT)
(check-expect (ghost-image "E") GHOST-RIGHT)
(check-expect (ghost-image "S") GHOST-CENTER)

(define (ghost-image direction)
  (cond [(string=? direction "N") GHOST-CENTER]
        [(string=? direction "S") GHOST-CENTER]
        [(string=? direction "E") GHOST-RIGHT]
        [(string=? direction "W") GHOST-LEFT]))


;; render-ghost : Ghost Image -> Image
; produces an image of the current state of the game

(check-expect (render-ghost GHOST1 BACKGROUND) (place-image (ghost-image "E") 80 80 BACKGROUND))
(check-expect (render-ghost GHOST2 BACKGROUND) (place-image (ghost-image "S") 140 100 BACKGROUND)) 

(define (render-ghost a-ghost bkg) 
  (place-image
   (ghost-image (ghost-direction a-ghost))
   (posn-x (ghost-position a-ghost))
   (posn-y (ghost-position a-ghost))
   bkg))





;; render-foods : List-of-posns Image -> Image
;; places the FOOD image at every posn in the given list on the given background

(check-expect (render-foods empty BACKGROUND) BACKGROUND)

(check-expect (render-foods (cons (make-posn 60 20) (cons (make-posn 80 40) empty))
                            BACKGROUND)
              (place-image FOOD 60 20 (place-image FOOD 80 40 BACKGROUND)))
(check-expect (render-foods (cons (make-posn 100 100)
                                  (cons (make-posn 60 20) (cons (make-posn 80 40) empty)))
                            BACKGROUND)
              (place-image FOOD 100 100
                           (place-image FOOD 60 20 (place-image FOOD 80 40 BACKGROUND))))


(define (render-foods a-lop bkg)
  (cond
    [(empty? a-lop) bkg]
    [(cons? a-lop) (render-food (first a-lop) 
                                (render-foods (rest a-lop) bkg))]))


;; render-food : Food -> Image

(check-expect (render-food (make-posn 80 40) BACKGROUND) (place-image FOOD 80 40 BACKGROUND)) 
(check-expect (render-food (make-posn 220 100) BACKGROUND) (place-image FOOD 220 100 BACKGROUND))

(define (render-food a-posn bkg)
  (place-image FOOD (posn-x a-posn) (posn-y a-posn) bkg))


; render-game : Game -> Image
 
(check-expect (render-game (make-game rac1 (list (make-posn 20 40) (make-posn 20 60)) LOG3 LOP3))
              (render-racman rac1
                             (render-walls (list (make-posn 20 40) (make-posn 20 60))
                                           (render-ghosts LOG3
                                                          (render-foods LOP3 BACKGROUND))))) 

(define (render-game a-game)
  (render-racman (game-racman a-game)
                 (render-walls (game-walls a-game)
                               (render-ghosts (game-ghosts a-game)
                                              (render-foods (game-foods a-game) BACKGROUND)))))


;; handle-key : Game KeyEvent -> Game
;; adapts the world based on keyboard input

(check-expect (handle-key (make-game rac1 (list (make-posn 20 40) (make-posn 20 60)) LOG3 LOP3) "down")
              (make-game (make-racman (make-posn 20 20) "S" 50 6)
                         (list (make-posn 20 40) (make-posn 20 60))
                         LOG3
                         LOP3))

(define (handle-key a-game a-kev)
  (make-game (make-racman
                  (make-posn (posn-x (racman-position (game-racman a-game)))
                             (posn-y (racman-position (game-racman a-game))))
                  (cond
                    [(string=? "left" a-kev) "W"]
                    [(string=? "right" a-kev) "E"]
                    [(string=? "up" a-kev) "N"]
                    [(string=? "down" a-kev) "S"] )
                  (racman-mouth (game-racman a-game))
                  (racman-wait (game-racman a-game)))
                 (game-walls a-game)
                 (game-ghosts a-game)
                 (game-foods a-game)))


;; game-over? : Game -> Boolean
;; take a game and produces whether or not the game should end

(check-expect (game-over? GAME1) false)
(check-expect (game-over? GAME2) true)
(check-expect (game-over? GAME3) true)

(define (game-over? a-game)
  (cond 
    [(empty? (game-foods a-game)) true]
    [(racman-dead? (racman-position (game-racman a-game)) (game-ghosts a-game)) true]
    [else false]))


;; racman-dead? : Posn List-of-ghosts -> Boolean
;; takes a game and produces if the racman is at the same Posn as a ghost

(check-expect (racman-dead? (make-posn 40 40) LOG1) false)
(check-expect (racman-dead? (make-posn 20 20) LOG2) false)
(check-expect (racman-dead? (make-posn 80 80) LOG3) true) 

(define (racman-dead? a-posn a-log)
  (cond
    [(empty? a-log) false]
    [(cons? a-log) (cond
                     [(posn-check a-posn (ghost-position (first a-log))) true] 
                     [else (racman-dead? a-posn (rest a-log))] ) ] ) )

;; Posn-check : Posn Posn -> Boolean
;; checks whether two Posns are equal

(check-expect (posn-check (make-posn 10 10) (make-posn 10 10)) true)
(check-expect (posn-check (make-posn 10 10) (make-posn 50 50)) false)

(define (posn-check posn1 posn2)
  (if
   (and (= (posn-x posn1) (posn-x posn2)) (= (posn-y posn1) (posn-y posn2)))
   true
   false))

;; render-game-over : Game -> Image
;; takes a game and produces a you win screen or a you lose screen

(check-expect (render-game-over GAME1) (render-game GAME1))
(check-expect (render-game-over GAME2) (overlay
                                        (text "You lose!" 100 "red")
                                        (render-game GAME2)))
(check-expect (render-game-over GAME3) (overlay
                                        (text "You win!" 100 "green")
                                        (render-game GAME3)))

(define (render-game-over a-game)
  (cond
    [(game-over? a-game) (cond
                           [(empty? (game-foods a-game)) (overlay
                                                          (text "You win!" 100 "green")
                                                          (render-game a-game))]
                           [(racman-dead? (racman-position (game-racman a-game)) (game-ghosts a-game)) (overlay
                                                                                                        (text "You lose!" 100 "red")
                                                                                                        (render-game a-game))])]
    [else (render-game a-game)])) 


;; update-game : Game -> Game
;; updates the state of the game with every tick of the game "clock"

(define (update-game a-game)
  (make-game
   (update-racman (game-racman a-game) (game-walls a-game))
   (game-walls a-game)
   (maybe-new-ghost (eat-food (game-racman a-game) (game-foods a-game)) (update-gang (game-ghosts a-game) (game-walls a-game)) (game-walls a-game))
   (update-foods (game-foods a-game) (game-racman a-game) (game-ghosts a-game))))
   
 





;; update-racman : Racman (Listof Posns) -> Racman
;; updates the state of a RacMan.
(check-expect (update-racman rac1 LOP1) (make-racman (make-posn 20 20) "E" -40 5))
(check-expect (update-racman (make-racman (make-posn 20 20) "N" 20 0) LOP1) (make-racman (make-posn 20 20) "N" 50 0))
(check-expect (update-racman (make-racman (make-posn 20 20) "E" 20 0) LOP1) (make-racman (make-posn 40 20) "E" 30 DELAY))
(check-expect (update-racman rac2 LOP1) (make-racman (make-posn 20 80) "S" 40 DELAY))
(check-expect (update-racman (make-racman (make-posn 20 60) "W" 30 0) LOP1) (make-racman (make-posn 20 60) "W" 50 0))
(check-expect (update-racman rac3 LOP1) (make-racman (make-posn 120 100) "N" 30 DELAY))
(check-expect (update-racman rac4 LOP1) (make-racman (make-posn 400 400) "N" 30 5))
(check-expect (update-racman (make-racman (make-posn 400 400) "S" 20 0) LOP1) (make-racman (make-posn 400 400) "S" 50 0))
(check-expect (update-racman (make-racman (make-posn 40 80) "W" 30 0) LOP3) (make-racman (make-posn 20 80) "W" 40 DELAY))
(check-expect (update-racman (make-racman (make-posn 40 80) "E" 10 0) LOP3) (make-racman (make-posn 40 80) "E" 50 0))

(define (update-racman a-racman a-lop)
  (cond
    [(= (racman-wait a-racman) 0)
     (cond
       [(and (string=? (racman-direction a-racman) "N") (is-wall? (make-posn (posn-x (racman-position a-racman)) (- (posn-y (racman-position a-racman)) 20)) a-lop)) (make-racman (racman-position a-racman) (racman-direction a-racman) 50 (racman-wait a-racman))  ]
       [(and (string=? (racman-direction a-racman) "E") (is-wall? (make-posn (+ (posn-x (racman-position a-racman)) 20) (posn-y (racman-position a-racman))) a-lop)) (make-racman (racman-position a-racman) (racman-direction a-racman) 50 (racman-wait a-racman))  ]
       [(and (string=? (racman-direction a-racman) "S") (is-wall? (make-posn (posn-x (racman-position a-racman)) (+ (posn-y (racman-position a-racman)) 20)) a-lop)) (make-racman (racman-position a-racman) (racman-direction a-racman) 50 (racman-wait a-racman))  ]
       [(and (string=? (racman-direction a-racman) "W") (is-wall? (make-posn (- (posn-x (racman-position a-racman)) 20) (posn-y (racman-position a-racman))) a-lop)) (make-racman (racman-position a-racman) (racman-direction a-racman) 50 (racman-wait a-racman))  ]
       [else (cond
               [(string=? (racman-direction a-racman) "N") (make-racman (make-posn (posn-x (racman-position a-racman)) (- (posn-y (racman-position a-racman)) 20)) "N" (if (>= (+ (racman-mouth a-racman) 10) 50)
                                                                                                                                                                          (- (+ (racman-mouth a-racman) 10) 100)
                                                                                                                                                                          (+ (racman-mouth a-racman) 10)) DELAY)]
               [(string=? (racman-direction a-racman) "E") (make-racman (make-posn (+ (posn-x (racman-position a-racman)) 20) (posn-y (racman-position a-racman))) "E" (if (>= (+ (racman-mouth a-racman) 10) 50)
                                                                                                                                                                          (- (+ (racman-mouth a-racman) 10) 100)
                                                                                                                                                                          (+ (racman-mouth a-racman) 10)) DELAY)]
               [(string=? (racman-direction a-racman) "S") (make-racman (make-posn (posn-x (racman-position a-racman)) (+ (posn-y (racman-position a-racman)) 20)) "S" (if (>= (+ (racman-mouth a-racman) 10) 50)
                                                                                                                                                                          (- (+ (racman-mouth a-racman) 10) 100)
                                                                                                                                                                          (+ (racman-mouth a-racman) 10)) DELAY)]
               [(string=? (racman-direction a-racman) "W") (make-racman (make-posn (- (posn-x (racman-position a-racman)) 20) (posn-y (racman-position a-racman))) "W" (if (>= (+ (racman-mouth a-racman) 10) 50)
                                                                                                                                                                          (- (+ (racman-mouth a-racman) 10) 100)
                                                                                                                                                                          (+ (racman-mouth a-racman) 10)) DELAY)]
               ) ] ) ]
    [else (make-racman (racman-position a-racman) (racman-direction a-racman)
                       (if (>= (+ (racman-mouth a-racman) 10) 50)
                           (- (+ (racman-mouth a-racman) 10) 100)
                           (+ (racman-mouth a-racman) 10)) (- (racman-wait a-racman) 1)) ] ) )

;; is-wall? : Posn List-of-Posns -> Boolean
;; takes a posn and determines whether or not it is on a wall

(check-expect (is-wall? (make-posn 0 0) LOP1) true)
(check-expect (is-wall? (make-posn 60 120) LOP1) false)
(check-expect (is-wall? (make-posn 0 0) LOP3) true)
(check-expect (is-wall? (make-posn 120 40) LOP3) true)
(check-expect (is-wall? (make-posn 340 360) LOP3) false)
(check-expect (is-wall? (make-posn 620 20) LOP3) true)
(check-expect (is-wall? (make-posn 20 420) LOP3) true)
(check-expect (is-wall? (make-posn 0 60) LOP3) true)
(check-expect (is-wall? (make-posn 60 0) LOP3) true)
(check-expect (is-wall? (make-posn 60 80) LOP3) true)

(define (is-wall? a-posn a-lop)
  (cond
    [(empty? a-lop) (or
                     (< (posn-x a-posn) 20)
                     (< (posn-y a-posn) 20)
                     (> (posn-x a-posn) 600)
                     (> (posn-y a-posn) 400))]
    [(cons? a-lop) (cond
                     [(posn-check a-posn (first a-lop)) true]
                     [else (is-wall? a-posn (rest a-lop))])]))

;; update-gang : (Listof Ghosts) (Listof Posns) -> (Listof Ghosts)
;; takes a list of ghosts and a list of wall posns and updates the movement of the ghosts

(check-expect (update-gang LOG1 LOP1) empty)
(check-expect (update-gang LOG3 LOP2) (list (make-ghost (make-posn 40 140) "S" 21 DELAY) (make-ghost (make-posn 80 80) "E" 55 5)))
(check-expect (update-gang (list (make-ghost (make-posn 320 220) "W" 1 0) GHOST3) LOP3) (list (make-ghost (make-posn 80 100) "W" 73 DELAY)))


(define (update-gang a-log a-lop)
  (cond
    [(empty? a-log) empty]
    [(cons? a-log) (if
                    (life-alert (update-ghost (first a-log) a-lop) )
                    (cons
                     (update-ghost (first a-log) a-lop)
                     (update-gang (rest a-log) a-lop))
                    (update-gang (rest a-log) a-lop) )]))



;; life-alert : Ghost -> Boolean
;; takes a ghost and produces true if the ghost is still alive

(check-expect (life-alert GHOST1) true)
(check-expect (life-alert GHOST2) true)
(check-expect (life-alert (make-ghost (make-posn 20 20) "S" 0 5)) false)

(define (life-alert a-ghost)
  (if
   (<= (ghost-life a-ghost) 0)
   false
   true))

;; update-ghost : Ghost (Listof Posns) -> Ghost
;; takes a ghost and a list of wall posns and update the state of the ghost

(check-expect (update-ghost GHOST1 LOP2) (make-ghost (ghost-position GHOST1) "E" 55 5))
(check-expect (update-ghost GHOST2 LOP3) (make-ghost (make-posn 140 120) "S" 73 DELAY))
(check-expect (update-ghost (make-ghost (make-posn 40 80) "E" 10 0) LOP3) (make-ghost (make-posn 40 80) "S" 10 0))
(check-expect (update-ghost (make-ghost (make-posn 20 20) "W" 44 0) LOP3) (make-ghost (make-posn 20 20) "N" 44 0))
(check-expect (update-ghost (make-ghost (make-posn 100 100) "N" 55 0) LOP1) (make-ghost (make-posn 100 80) "N" 54 DELAY))
(check-expect (update-ghost (make-ghost (make-posn 200 200) "W" 69 0) LOP1) (make-ghost (make-posn 180 200) "W" 68 DELAY))

(define (update-ghost a-ghost a-lop)
  (cond
    [(= (ghost-wait a-ghost) 0)
     (cond
       [(and (string=? (ghost-direction a-ghost) "N") (is-wall? (make-posn (posn-x (ghost-position a-ghost)) (- (posn-y (ghost-position a-ghost)) 20)) a-lop)) (make-ghost (ghost-position a-ghost) (ghost-turn a-ghost) (ghost-life a-ghost) (ghost-wait a-ghost))  ]
       [(and (string=? (ghost-direction a-ghost) "E") (is-wall? (make-posn (+ (posn-x (ghost-position a-ghost)) 20) (posn-y (ghost-position a-ghost))) a-lop)) (make-ghost (ghost-position a-ghost) (ghost-turn a-ghost) (ghost-life a-ghost) (ghost-wait a-ghost))  ]
       [(and (string=? (ghost-direction a-ghost) "S") (is-wall? (make-posn (posn-x (ghost-position a-ghost)) (+ (posn-y (ghost-position a-ghost)) 20)) a-lop)) (make-ghost (ghost-position a-ghost) (ghost-turn a-ghost) (ghost-life a-ghost) (ghost-wait a-ghost))  ]
       [(and (string=? (ghost-direction a-ghost) "W") (is-wall? (make-posn (- (posn-x (ghost-position a-ghost)) 20) (posn-y (ghost-position a-ghost))) a-lop)) (make-ghost (ghost-position a-ghost) (ghost-turn a-ghost) (ghost-life a-ghost) (ghost-wait a-ghost))  ]
       [else (cond
               [(string=? (ghost-direction a-ghost) "N") (make-ghost (make-posn (posn-x (ghost-position a-ghost)) (- (posn-y (ghost-position a-ghost)) 20)) "N" (- (ghost-life a-ghost) 1) DELAY)]
               [(string=? (ghost-direction a-ghost) "E") (make-ghost (make-posn (+ (posn-x (ghost-position a-ghost)) 20) (posn-y (ghost-position a-ghost))) "E" (- (ghost-life a-ghost) 1) DELAY)]
               [(string=? (ghost-direction a-ghost) "S") (make-ghost (make-posn (posn-x (ghost-position a-ghost)) (+ (posn-y (ghost-position a-ghost)) 20)) "S" (- (ghost-life a-ghost) 1) DELAY)]
               [(string=? (ghost-direction a-ghost) "W") (make-ghost (make-posn (- (posn-x (ghost-position a-ghost)) 20) (posn-y (ghost-position a-ghost))) "W" (- (ghost-life a-ghost) 1) DELAY)]
               ) ] ) ]
    [else (make-ghost (ghost-position a-ghost) (ghost-direction a-ghost) 
                       (ghost-life a-ghost) (- (ghost-wait a-ghost) 1)) ] ) )


;; ghost-turn : Ghost -> Direction
;; takes a ghost and changes its direction by 90 degrees

(check-expect (ghost-turn GHOST1) "S")
(check-expect (ghost-turn GHOST2) "W")

(define (ghost-turn a-ghost)
  (cond
    [(string=? (ghost-direction a-ghost) "N") "E"]
    [(string=? (ghost-direction a-ghost) "E") "S"]
    [(string=? (ghost-direction a-ghost) "S") "W"]
    [(string=? (ghost-direction a-ghost) "W") "N"] ) )


;; update-foods : (Listof Posns) Racman (Listof Ghosts) -> (Listof Posns)
;; takes a list of food posns, a racmman, and a gang of ghosts and updates the list of food posns
;;  based on if a racman has eaten the food or a ghost has died and spawned a new food

(check-expect (update-foods LOP1 rac1 LOG1) empty)
(check-expect (update-foods LOP3 rac1 LOG3) LOP3)
(check-expect (update-foods LOP3 (make-racman (make-posn 120 40) "S" 40 5) LOG2) (list POSN2))
(check-expect (update-foods LOP3 rac1 (list (make-ghost (make-posn 200 200) "S" 0 5) (make-ghost (make-posn 160 260) "W" 52 3))) (cons (make-posn 200 200) LOP3))

(define (update-foods a-lop a-racman a-log)
  (cond
    [(empty? a-lop) empty]
    [(cons? a-lop) (cond
                     [(posn-check (racman-position a-racman) (first a-lop)) (update-foods (rest a-lop) a-racman a-log)]
                     [(any-dead? a-log) (append (dead-list-posns a-log) a-lop)]
                     [else (cons (first a-lop) (update-foods (rest a-lop) a-racman a-log))])]))

;; dead-list-posns : (Listof Ghosts) -> (Listof Posns)
;; takes a list of ghosts and returns a list of the posns of all dead ghosts


(define (dead-list-posns a-log)
  (cond
    [(empty? a-log) empty]
    [(cons? a-log) (cons
                    (cond
                      [(not (any-dead? a-log)) empty]
                      [else (ghost-position (first a-log))])                                      ;;   !!!!!!! NEW CODE I JUST WROTE TO TRY TO SOLVE ISSUES, DOES NOT HAVE
                    (dead-list-posns (rest a-log)))]))                                            ;;           CHECK EXPECTS WRITTEN YET



;; any-dead? : (Listof Ghosts) -> Boolean
;; takes a list of ghots and returns true if any of the ghosts in the list are dead


(define (any-dead? a-log)
  (cond
    [(empty? a-log) false]
    [(cons? a-log) (cond
                     [(boolean=? (life-alert (first a-log)) false) true]                          ;;   !!!!!!! NEW CODE I JUST WROTE TO TRY TO SOLVE ISSUES, DOES NOT HAVE
                     [else (any-dead? (rest a-log))])]))                                          ;;           CHECK EXPECTS WRITTEN YET

;; likelihood percentage of a new ghost coming into existence
(define GHOST-SPAWN% 10)

;; maybe-new-ghost : Boolean List-of-ghosts List-of-walls -> List-of-ghosts
;; produces a list of ghosts where a new ghost is added with GHOST-SPAWN%
;; probability to the given list of ghosts, IF the trigger? is true. The
;; list of walls is used to make sure the new ghost's location is not the
;; same as the Posn of an existing wall

; can't use check-expect to write tests because of randomness
; there is a (check-random ) we could use, but I can't write the
; tests because it will depend on your definition of ghosts

(define (maybe-new-ghost trigger? a-log walls)
  (cond [(not trigger?) a-log]
        [(< (random 100) GHOST-SPAWN%)
;; make a ghost according to how you defined the structure; 
;; use the (random-empty-loc ...) to pick an initial position for it *******
         (cons (make-ghost (random-empty-loc walls) "E" 100 DELAY) a-log)]  
        [else a-log]))

 
;; random-empty-loc : List-of-Posns -> Posn
;; generates a random posn at a location that is a multiple of the CELL-SIZE
;; and that is not already in the given list of posns

(define (random-empty-loc a-lop)
  (check-or-pick-another-random
   a-lop
   (make-posn (* CELL-SIZE (+ 1 (random (- CELLS-WID 2))))
              (* CELL-SIZE (+ 1 (random (- CELLS-HIGH 2)))))))

;; check-or-pick-another-random : List-of-posns Posn -> Posn
;; accepts the given posn if it is not in the list of existing posns
;; but otherwise it uses random-empty-loc to generate a different
;; random posn

(define (check-or-pick-another-random a-lop a-posn)
  (if (is-wall? a-posn a-lop)   ;; assumes you have written an   is-wall? function *******
      (random-empty-loc a-lop)
      a-posn))



;; eat-food : Racman (Listof Posns) -> Boolean
;; takes a racman and a list of food posns and outputs true if the racman eats a food

(check-expect (eat-food rac1 LOP1) false)
(check-expect (eat-food rac1 LOP2) false)
(check-expect (eat-food rac1 (list (make-posn 20 20) (make-posn 100 100))) true)

(define (eat-food a-racman a-lop)
  (cond
    [(empty? a-lop) false]
    [(cons? a-lop) (if
                    (posn-check (racman-position a-racman) (first a-lop))
                    true
                    (eat-food a-racman (rest a-lop)))]))
  

;; run : Game -> Game
(define (run a-game)
  (big-bang a-game
    (to-draw render-game)
    (on-key handle-key)
    (stop-when game-over? render-game-over)
    (on-tick update-game) 
    ))