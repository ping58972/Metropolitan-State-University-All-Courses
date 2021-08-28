;; @author: Nalongsone Danddank	Student ID : 14958950	StarID: jf3893pd
;; Email: nalongsone.danddank@my.metrostate.edu\
;; ICS 365. Organization of Programming Languages. 
;; Programming Assignment 4

#!r6rs
(import (rnrs))

;; 1. Write a scheme function named up-to-first-char that takes a list as its input...
(define (up-to-first-char ls)
  (define value)
  (define (fn-helper lst)
        (cond
          [(null? lst) '()]
          [(symbol? (car lst)) '()]  
          [else (cons  (car lst)
                  (fn-helper (cdr lst)))]
          ))
  (if (null? (fn-helper ls))
      (begin
      (set! value 'NIL))
      (begin
      (set! value (fn-helper ls))))
  value)
;;display the testing result of Quest 1.
(display (up-to-first-char '(1 2 3 4 5 a b c)))
(newline)
(display (up-to-first-char '(8 7 5 f)))
(newline)
(display (up-to-first-char '(1 2 9)))
(newline)
(display (up-to-first-char '(z w x)))
(newline)

;; 2. Write a Scheme function that takes a list and an atom as parameters and ...
(define (delete-top-level atom lst)
  (cond
    [(null? lst) '()]
    [(equal? atom (car lst)) (delete-top-level atom (cdr lst))]
    [else (cons (car lst) (delete-top-level atom (cdr lst)))]))
;;display the testing result of Quest 2.
(define lsts '(1 2 3 45 6 (1 2) q e cc))
(display (delete-top-level 'q lsts))
(newline)
(display (delete-top-level '(1 2) lsts))
(newline)

;; 3. Write a Scheme function that takes two atoms and a list as parameters and...
(define (replace atom-1 atom-2 lst)
    (cond
        ((null? lst) '() )
        ((list? (car lst)) (cons (replace atom-1 atom-2 (car lst)) (replace atom-1 atom-2 (cdr lst))))
        ((= (car lst) atom-1) (cons atom-2 (replace atom-1 atom-2 (cdr lst))))
        (else (cons (car lst) (replace atom-1 atom-2 (cdr lst))))
    )
)
;;display the testing result of Quest 3.
(define ls '(1 2 (1 3)3 45 6))
(display (replace 1 9 ls))
(newline)
(display (replace 3 0 ls))