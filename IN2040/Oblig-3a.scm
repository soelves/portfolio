;;Oblig 3a - solvesel

(load "prekode3a.scm")

;; 1
;; a + b)

;; Backup løsningen, unmemoize, er litt primitiv, men fungerer (kun en om gangen).
(define backup '())

(define (mem kodeord p)
  (cond ((equal? kodeord 'memoize)
         (and
          (set! backup p)
          (let ((table (make-table)))
            (lambda x
              (let ((prev-res (lookup x table)))
                (or prev-res
                    (let ((result (apply p x)))
                      (insert! x result table)
                      result)))))))
        ((equal? kodeord 'unmemoize) backup)))
         

    
(set! fib (mem 'memoize fib))
(fib 3)
(fib 3)
(fib 2)
(fib 4)

(set! fib (mem 'unmemoize fib))
(fib 3)

(set! test-proc (mem 'memoize test-proc))
(test-proc)
(test-proc 40 41 42 43 44)
(test-proc 40 41 42 43 44)
(test-proc 42 43 44)

;; c)

(define mem-fib (mem 'memoize fib))
(mem-fib 3)
(mem-fib 3)
(mem-fib 2)


;; Det virker som at det er kun resultatet som blir memorisert, siden vi bruker
;; define istedenfor set!

(newline)
;; d)
;; Synes oppgaven var litt uklar på akkurat hva hjelpeprosedyren skulle være, håper
;; det jeg gjorde var bra nok :)

(define (populate table x)
  (if (> 3 (length x))
      (insert! (car x) (car (cdr x)) table)
      (and
       (insert! (car x) (car (cdr x)) table)
       (populate table (cddr x)))))
  
(define (greet . x)
  (if (= 0 (length x))
      (begin (display "good day friend") (newline))
      (let ((table (make-table)))
        (begin
          (populate table x)
          (display "good ")
          (if (lookup 'time table)
              (begin (display (lookup 'time table)) (display " "))
              (begin (display "day ")))
          (if (lookup 'title table)
              (begin (display (lookup 'title table)) (display " "))
              (begin (display "friend")))
          (newline)))))


(greet)
(greet 'time "evening")
(greet 'title "sir" 'time "morning")
(greet 'time "afternoon" 'title "dear")

(newline)
;; 2
;; a)

(define (list-to-stream l)
   (if (null? l)
       the-empty-stream
       (cons-stream (car l) (list-to-stream (cdr l)))))

(define (stream-to-list s . n)
  (if (null? n)
      (if (null? s)
          '()
          (cons (stream-car s) (stream-to-list (stream-cdr s))))
      (if (= 0 (car n))
          '()
          (cons (stream-car s) (stream-to-list (stream-cdr s) (- (car n) 1))))))


(define strøm (list-to-stream '(1 2 3 4 5)))

strøm
(stream-car strøm)
(stream-car (stream-cdr (stream-cdr strøm)))

(define liste (stream-to-list (stream-interval 5 10)))
liste
(newline)
(list-to-stream '(1 2 3 4 5))
(stream-to-list (stream-interval 10 20))
(show-stream nats 15)
(stream-to-list nats 10)

(newline)
;; b)

(define (stream-map proc . argstreams)
  (if (strøm-slutt (map null? argstreams))
      the-empty-stream
      (cons-stream
       (apply proc (map stream-car argstreams))
       (apply stream-map
              (cons proc (map stream-cdr argstreams))))))

;; Hjelpemetode for å sjekke om en av strømmene er ferdige. Tar imot en liste av
;; #t og #f. Hvis #t, så er en av strømmene tomme.

(define (strøm-slutt t-or-f)
   (if (and
        (= 1 (length t-or-f))
        (not (car t-or-f)))
       #f
       (if (car t-or-f)
           #t
           (strøm-slutt (cdr t-or-f)))))


;; Eksempel på bruk av stream-map med +

(define strøm1 (list-to-stream '(1 2 3 4 5)))
(define strøm2 (list-to-stream '(6 7 8 9)))
(define strøm3 (list-to-stream '(10 20 30 40 50 60 70)))



(stream-map + strøm1 strøm2 strøm3)
(show-stream (stream-map + strøm1 strøm2 strøm3) 2)
(show-stream (stream-map + strøm1 strøm2 strøm3))


(newline)
;; c)
;; Et potensielt problem jeg kan se vil være uendelige strømmer, da
;; remove-duplicates vil potensielt kunne gå uten å stoppe.


;; d)

(define (remove-duplicates stream)
  (if (stream-null? stream)
      the-empty-stream
      (cons-stream (stream-car stream)
                   (remove-duplicates
                    (stream-filter
                     (lambda (x) (not (equal? (stream-car stream) x))))))))

;; e)

(define (show x)
  (display x)
  (newline)
  x)

(define x
  (stream-map show
              (stream-interval 0 10)))

(stream-ref x 5)
(stream-ref x 7)

(stream-ref x 3)

;; Det printes ut 0 1 2 3 4 5 og så x som er 5 med det første kallet, og deretter
;; 6 7 og så x som er 7 med det andre kallet. Siden x er strømmen vi beveger oss
;; fremmover i, så ser vi prosessen hvor nye cons celler blir innvilget, og så
;; printes verdien ut etter vi har "låst opp" den cons cellen. Slang derfor
;; med stream-ref x 3, for å vise at siden vi allerede har gått opp til 7,
;; så vil 3 være låst opp, og ingen andre tall blir "printet ut".

(newline)
;; f)

(define (mul-streams . argstreams)
  (apply stream-map
         (cons * argstreams)))


(mul-streams strøm1 strøm2 strøm3)
(show-stream (mul-streams strøm1 strøm2 strøm3))


(newline)
;; g)

;;(define factorials (cons-stream (mul-streams nats)))

  