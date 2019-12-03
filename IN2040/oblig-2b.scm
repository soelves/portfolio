;; Oblig 2b - solvesel

;; 1
;; a)

(define (make-counter)
  (let ((count 0))
    (lambda ()
      (set! count (+ count 1))
      count)))


(define count 42)
(define c1 (make-counter))
(define c2 (make-counter))

(c1)
(c1)
(c1)
count
(c2)


;; b) Tegning, se vedlegg (ikke enda utført).

;; 2
;; a)

(define (make-stack x)
  (define (pop!)
    (if (not (null? x))
        (set! x (cdr x))))
  (define (push! objects)
    (define (init liste)
      (if (not (null? liste))
          (let ((new (cons (car liste) x)))
            (if (not (null? x))
                (set-cdr! new x))
            (set! x new)))
      (if (not (null? liste))
              (init (cdr liste))))
     (init objects))
  (define (dispatch message . args)
    (cond ((eq? message 'stack) x)
          ((eq? message 'pop!) (pop!))
          ((eq? message 'push!) (push! args))))
  dispatch)

(define s1 (make-stack (list 'foo 'bar)))
(define s2 (make-stack '()))

(s1 'pop!)
(s1 'stack)
(s2 'pop!)
(s2 'push! 1 2 3 4)
(s2 'stack)
(s1 'push! 'bah)
(s1 'push! 'zap 'zip 'baz)
(s1 'stack)


;; b)

(define (pop! s)
  (s 'pop!))

(define (stack s)
  (s 'stack))

(define (push! s . obj)
  (for-each (lambda (x)
              (s 'push! x))
            obj))

(pop! s1)
(stack s1)
(push! s1 1 2 3)
(stack s1)


;; 3 - (member og) memq (for å sjekke om noe er en del av en liste)

;; a) Tegning vedlagt
(define bar (list 'a 'b 'c 'd 'e))
(set-cdr! (cdddr bar) (cdr bar))
bar
(list-ref bar 0)
(list-ref bar 3)
(list-ref bar 4)
(list-ref bar 5)

;; b) Tegning vedlagt
(define bah (list 'bring 'a 'towel))
(set-car! bah (cdr bah))
bah
(set-car! (car bah) 42)
bah

;; c)
;; Så ikke oppgave d før jeg gjorde c...
(define (cycle? liste)
 (if (list? liste)
     #f
     #t))

(cycle? '(hey ho))
(cycle? '(la la la))
(cycle? bah)
(cycle? bar)

;; d)
;; Siden en sykel ikke er klassifisert som en liste, vil et kall på list? gi #f.

;; e)

(define (make-ring ring)
  (define ring-length 0)
  (define (make)
    (begin
      (set! ring-length (length ring))
      (set-cdr! (list-tail ring (- ring-length 1)) ring)))
  (define (top)
    (car ring))
  (define (left-rotate!)
    (and (set! ring (cdr ring))
         (top)))
  (define (right-rotate!)
    (let ((start (top)))
      (define (init r)
        (if (eq? start (car (cdr ring)))
            (top)
            (init (left-rotate!))))
      (init ring)))
  (define (delete!)
    (and (delete-help)
         (top)))
  (define (delete-help)
    (define (init l n)
      (if (< n ring-length)
          (and (right-rotate!)
               (init (cons (car ring) l) (+ n 1)))
          (and (set! ring l)
               (make))))
         (init '() 1))
  (define (insert! x)
    (and (insert-help x)
         (top)))
  (define (insert-help x)
    (define (init l n)
      (if (< n ring-length)
          (and (right-rotate!)
               (init (cons (car ring) l) (+ n 1)))
          (and (set! ring (append x l))
               (make))))
         (init '() 0))
  (define (set-test!)
    (set! ring 4))
  (define (dispatch message . args)
    (cond ((eq? message 'set-test!) (set-test!))
          ((eq? message 'ring) ring)
          ((eq? message 'make) (make))
          ((eq? message 'top) (top))
          ((eq? message 'right-rotate!) (right-rotate!))
          ((eq? message 'left-rotate!) (left-rotate!))
          ((eq? message 'delete!) (delete!))
          ((eq? message 'insert!) (insert! args))
          ((eq? message 'length) ring-length)
          (else "Feil input")))
  dispatch)


(define (top r)
  (r 'top))

(define (right-rotate! r)
  (r 'right-rotate!))

(define (left-rotate! r)
  (r 'left-rotate!))

(define (delete! r)
  (r 'delete!))

(define (insert! r x)
  (r 'insert! x))

;; Trengte en make før jeg kalte på funksjoner, håper det går fint! :)
;; Make tar en liste og gjør den om til en sykel, samt lagrer lengden av lista som var.

(define r1 (make-ring '(1 2 3 4)))
(define r2 (make-ring '(a b c d)))
(r1 'make)
(r2 'make)

(top r1)
(top r2)
(right-rotate! r1)
(left-rotate! r1)
(left-rotate! r1)
(delete! r1)
(left-rotate! r1)
(left-rotate! r1)
(left-rotate! r1)
(insert! r2 'x)
(right-rotate! r2)
(left-rotate! r2)
(left-rotate! r2)
(top r1)

;; f)
#|
left-rotate! har konstant kompleksitet, da det eneste den gjør er å cdr ringen.

right-rotate! Går gjennom hele ringen før den møter på starten, og stopper der,
så den har kompleksitet O(n), da den MÅ gå gjennom hele ringen.


insert! og delete! har begge kompleksitet O(n^2), da de roterer lista til høyre for
hvert av elementene som går inn i den nye lista, som så blir gjort om til en ring. Så
for hvert element må den igjen gå gjennom hvert element, altså n^2. 
Hvis tidskompleksitet var viktigere i denne oppgaven ville jeg nok prøvd å kommet opp
med en bedre løsning for insert og delete.
|#










