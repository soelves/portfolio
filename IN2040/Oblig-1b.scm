#|
solvesel

Beklager at jeg leverte hele oppgaven i pdf sist :/
|#

;;; 1.
;;; f)

(define liste-f '(0 42 #t bar))

(car(cdr liste-f))


;;; g)

(define liste-g '((0 42) (#t bar)))

(car (cdr (car liste-g)))
;;; Den første car'en er for å fjerne () rundt 42.

;;; h)

(define liste-h '((0) (42 #t) (bar)))

(car(car(cdr liste-h)))

;;; i)
;;; Usikker på om jeg kan conse en slik liste og unngå "." mellom parene?

(cons (cons 0 42) (cons (cons #t 'bar) '()))

(list (list 0 42) (list #t 'bar))


;;; 2
;;; a)


(define (length2 l)
  (define (iter items count)
    (if (null? items)
        count
        (iter (cdr items)
              (+ count 1))))
  (iter l 0))

(length2 '())
(length2 '(1 2 3 4 9))

;;; b)

(define (rev-list items)
  (define (iter in out)
    (if (null? in)
        out
        (iter (cdr in)
              (cons (car in) out))))
  (iter items '()))

(rev-list '(1 2 3 4))
(rev-list '("towel" "a" "bring" "always"))
(rev-list '())

;;; c)

(define (all? pred items)
  (if (null? items)
      #t
      (if(pred (car items))
         (all? pred (cdr items))
         #f)))

(all? odd? '(1 3 5 7))
(all? odd? '(1 3 4 7))
(all? odd? '())
(all? zero? '(0 0 0))
(all? zero? '(0 1 0))
(all? zero? '())

;;; Antar her at høyere enn 10 betyr til og med 10, derfor er 10 lov men ikke høyere.

(all? (lambda (x)
        (if (> 11 x) #t #f)) '(1 2 3 4 5))

(all? (lambda (x)
        (if (> 11 x) #t #f)) '(1 2 3 4 50))
  
;;; d)

(define (nth indeks items)
  (if (zero? indeks)
      (car items)
      (nth (- indeks 1) (cdr items))))

(nth 2 '(47 11 12 13))
(nth 0 '(47 11 12 13))

;;; e)

(define (where n l)
  (define (iter count items)
    (if (null? items)
        #f
        (if (= n (car items))
            count
            (iter (+ count 1) (cdr items)))))
    (iter 0 l))

(where 3 '(1 2 3 4 5 3))
(where 0 '(1 2 3 4 5 3))

;;; f)

(define (map2 proc items1 items2)
  (if (null? items1)
      '()
      (if(null? items2)
         '()
         (cons (proc (car items1) (car items2))
               (map2 proc (cdr items1) (cdr items2))))))

(map2 + '(1 2 3 4) '(3 4 5))
(map2 + '(3 4 5) '(1 2 3 4))

;;; g)

(map2 (lambda (x y)
  (/ (+ x y) 2)) '(1 2 3 4) '(3 4 5))


;;; h)

(define (both? pred)
  (lambda (x y) (and (pred x) (pred y))))

(map2 (both? even?) '(1 2 3) '(3 4 5))
((both? even?) 2 4)
((both? even?) 2 5)
            
;;; i)

(define (self pros)
  (lambda (x) (pros x x)))

((self +) 5)
((self *) 3)
(self +)
((self list) "hello")


