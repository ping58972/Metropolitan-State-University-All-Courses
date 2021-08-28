#!r6rs
(import (rnrs))
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

(define (reverse lst)
  (reverse-helper lst '()))
(define (reverse-helper lst lst_help)
  (if (null? lst) lst_help
      (reverse-helper (cdr lst) (cons (car lst) lst_help))))