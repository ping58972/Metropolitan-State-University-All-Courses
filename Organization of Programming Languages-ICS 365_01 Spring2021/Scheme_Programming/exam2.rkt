;; @author: Nalongsone Danddank	Student ID : 14958950	StarID: jf3893pd
;; Email: nalongsone.danddank@my.metrostate.edu\
;; ICS 365. Organization of Programming Languages. 
;; Exam 2

#!r6rs
(import (rnrs))

(define (atom? x)
  (and (not (null? x))
       (not (pair? x))))

(define (findlocate  atm  lst)
  (cond ((not (list? lst)) 0)
        ((null? lst) 1)
        ((and (atom? (car lst)) (equal? (car lst) atm)) 1)
        (else (+ 1 (findlocate atm (cdr lst))))))

