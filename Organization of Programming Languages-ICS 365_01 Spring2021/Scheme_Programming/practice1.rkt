#!r6rs
(import (rnrs))
(define factorial (lambda (n)
                    (if (< n 2)
                        1
                        (* n (factorial (- n 1))))))

(define list-length (lambda (lst)
                      (if (null? lst)
                          0
                          (+ 1 (list-length (cdr lst))))))

(define sum-list (lambda (lst)
                   (if (null? lst)
                       0
                       (+ (sum-list (cdr lst)) (car lst)))))

(define prod-list (lambda (lst)
                    (if (null? lst)
                        1
                        (* (prod-list (cdr lst)) (car lst)))))


(define reduce-list (lambda (lst base f)
                      (if (null? lst)
                          base
                          (f (car lst) (reduce-list (cdr lst) base f)))))
(define lst '(1 2 3 4 5 6))

(reduce-list lst '() (lambda (e r) (cons (* 2 e) r)))




(define (func obj)
  (cond
    [(symbol? obj) (display "symbol")]
    [(char? obj) (display "char")]
    [(string? obj) (display "string")]
    [(number? obj) (display "number")]
    [else (display "ohter")]))

(define lsts '(1 2 3 45 6 q e cc))


(define (my-map fn lst)
  (if (null? lst)
      '()
      (cons (fn (car lst))
            (my-map fn (cdr lst)))))

(define (mmap fn lst)
  (if (symbol? (car lst))
      '()
      (cons (fn (car lst))
            (mmap fn (cdr lst)))))

(define (mmmap lst)
  (if (symbol? (car lst))
      '()
      (cons  (car lst)
             (mmmap  (cdr lst)))))

(define (mmp lst)
  (cond
    [(null? lst) '()]
    [(symbol? (car lst)) '()]  
    [else (cons  (car lst)
                 (mmp  (cdr lst)))]
    ))

(define delete
  (lambda (item list)
    (cond
      ((equal? item (car list)) (cdr list))
      (else (cons (car list) (delete item (cdr list)))))))

(define delete
  (lambda (item list)
    (cond
     ((equal? item (car list)) (cdr list))
     (else (cons (car list) (delete item (cdr list)))))))