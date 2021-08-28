#lang racket
(define (x lis)
    (cond
      ((null? lis) 0)
      ((not (list? (car lis)))
         (cond
             ((eq? (car lis) #f) (x (cdr lis)))
             (else (+ 1 (x (cdr lis))))
          )
       )
      (else
       (+ (x (car lis))
          (x (cdr lis)))
       )
    )
  )

(define (reverse lst)
  (reverse-helper lst '()))
(define (reverse-helper lst lst_help)
  (if (null? lst) lst_help
      (reverse-helper (cdr lst) (cons (car lst) lst_help))))