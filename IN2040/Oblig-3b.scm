;; Oblig 3b - solvesel

(load "evaluator.scm")

(set! the-global-environment (setup-environment))

;; 1a)

#|
For (foo 2 square) får vi 0. Dette er fordi foo er en prosedyre som sjekker om
variablen cond som vi sender med er lik 2. Hvis ja, så returneres 0.

For (foo 4 square), får vi 16. Når foo ikke har cond lik 2, så utføres prosedyren som blir
sendt med i foo, i dette tilfelle square.

I det siste eksempelet så får vi 2. Prosedyren cond sjekker om variablen cond i vår evaluator
er lik 2. Det er den ikke, den er definert som 3. Derfor går vi videre til else, som
utfører prosedyren else vi har definert i vår evaluator, som er å dele noe på 2. Her deler
vi 4 på 2, og får 2.

foo blir ikke påvirket av navnene else og cond, da disse er variabelnavn som sendes med, og blir
satt til først 2 og square, så 4 og square. Prosedyrene cond og else fungerer som vanlig da
de er i listen av prosedyrer, og selv om en definerer egne definisjoner av dem, vil de gamle
prosedyrene ved samme navn bli hentet fra listen over prosedyrer når de er i prosedyre posisjon.
Denne listen blir ikke endret når en definerer det i REPL'et vårt, selv når det har samme navn.
Det vi ender opp med er en primitiv og en ny prosedyre med samme navn, noe som er fult lov i
vår metascheme.
|#

;; 2a)

;; La til 1+ og 1- under primitive-procedures i evaluatoren. 
;;(read-eval-print-loop)

(define primitive-procedures
  (list (list 'car car)
        (list 'cdr cdr)
        (list 'cons cons)
        (list 'null? null?)
        (list 'not not)
        (list '+ +)
        (list '- -)
        (list '* *)
        (list '/ /)
        (list '= =)
        (list 'eq? eq?)
        (list 'equal? equal?)
        (list 'display 
              (lambda (x) (display x) 'ok))
        (list 'newline 
              (lambda () (newline) 'ok))
        (list '1+
              (lambda (x) (+ x 1)))
        (list '1-
              (lambda (x) (- x 1)))
;;      her kan vi legge til flere primitiver.
        ))
(set! the-global-environment (setup-environment))


;; 2b)
(display "2b)") (newline)

;; Usikker på om jeg skulle løse oppgaven uten den globale omgivelsen eller ikke,
;; antar at det er ok å bruke den.

;; Endringer av evaluator, lagt til alternativ for primitiver.
(define (eval-special-form exp env)
  (cond ((quoted? exp) (text-of-quotation exp))
        ((primitive-procedure? exp)                                      ;; Her
         (define-variable! (definition-variable exp)
                           (list 'primitive (definition-value exp)) env)) 
        ((definition? exp) (eval-definition exp env))
        ((if? exp) (eval-if exp env))
        ((lambda? exp)
         (make-procedure (lambda-parameters exp)
                         (lambda-body exp)
                         env))
        ((begin? exp) 
         (eval-sequence (begin-actions exp) env))
        ((cond? exp) (mc-eval (cond->if exp) env))))

(define (special-form? exp)
  (cond ((quoted? exp) #t)
        ((primitive-procedure? exp) #t) ;; Her
        ((assignment? exp) #t)
        ((definition? exp) #t)
        ((if? exp) #t)
        ((lambda? exp) #t)
        ((begin? exp) #t)
        ((cond? exp) #t)
        (else #f)))

(define (install-primitive! name pros)
  (mc-eval (list 'primitive name pros) the-global-environment))

(set! the-global-environment (setup-environment))

(mc-eval '(define (test1 x) (+ x 3)) the-global-environment)
(install-primitive! 'square (lambda (x) (* x x)))

the-global-environment
(mc-eval '(square 4) the-global-environment)
(mc-eval '(test1 4) the-global-environment)

;;the-global-environment

;;(read-eval-print-loop)

(newline)
;; 3a

(display "3a)") (newline)

;; Meta and
(define (m-and exp env)
  (if (mc-eval (cadr exp) env)
      (if (mc-eval (caddr exp) env)
          #t
          #f)
      #f))

;; Meta or
(define (m-or exp env)
  (if (mc-eval (cadr exp) env)
      #t
      (if (mc-eval (caddr exp) env)
          #t
          #f)))

;; Hjelp til eval-special-form og special-form?
(define (and? exp) (tagged-list? exp 'and))
(define (or? exp) (tagged-list? exp 'or))


(define (eval-special-form exp env)
  (cond ((quoted? exp) (text-of-quotation exp))
        ((assignment? exp) (eval-assignment exp env))
        ((definition? exp) (eval-definition exp env))
        ((if? exp) (eval-if exp env))
        ((and? exp) (mc-eval (m-and exp env) env))        ;;and sjekk
        ((or? exp) (mc-eval (m-or exp env) env))        ;;or sjekk
        ((lambda? exp)
         (make-procedure (lambda-parameters exp)
                         (lambda-body exp)
                         env))
        ((begin? exp) 
         (eval-sequence (begin-actions exp) env))
        ((cond? exp) (mc-eval (cond->if exp) env))))

(define (special-form? exp)
  (cond ((quoted? exp) #t)
        ((assignment? exp) #t)
        ((definition? exp) #t)
        ((if? exp) #t)
        ((and? exp) #t)
        ((or? exp) #t)
        ((lambda? exp) #t)
        ((begin? exp) #t)
        ((cond? exp) #t)
        (else #f)))

(set! the-global-environment (setup-environment))



;;Test for and og or.

(mc-eval '(and #t #t) the-global-environment)
(mc-eval '(and #t #f) the-global-environment)
(mc-eval '(and #f #f) the-global-environment)
(mc-eval '(and (= 1 1) (= 2 2)) the-global-environment)
(mc-eval '(and (= 1 3) (= 2 2)) the-global-environment)
(mc-eval '(and (= 1 3) (= 2 3)) the-global-environment)

(mc-eval '(or #t #t) the-global-environment)
(mc-eval '(or #f #t) the-global-environment)
(mc-eval '(or #f #f) the-global-environment)
(mc-eval '(or (= 1 1) (= 2 2)) the-global-environment)
(mc-eval '(or (= 1 3) (= 2 2)) the-global-environment)
(mc-eval '(or (= 1 3) (= 2 3)) the-global-environment)


(newline)
;; 3b)

(display "3b)") (newline)

(define (if-predicate exp) (cadr exp))

(define (if-consequent exp) (caddr exp))

(define (else-conseq exp)
  (cadr exp))

(define (hopp-3 exp)
  (cdddr exp))

(define (hopp-4 exp)
  (cddddr exp))

(define (hopp-5 exp)
  (cdr (cddddr exp)))

(define (if-alternative exp)
  (if (not (null? (cdddr exp)))
      (cadddr exp)
      'false))

(define (eval-if exp env)
  (if (true? (mc-eval (if-predicate exp) env))
      (mc-eval (car (hopp-3 exp)) env)
      (if (eq? 'else (car exp))
          (else-conseq exp)
          (if (eq? 'elsif (car (hopp-4 exp)))
              (eval-if (hopp-4 exp) env)
              (mc-eval (car (hopp-5 exp)) env)))))


(set! the-global-environment (setup-environment))

;; Test av ny if.
;; Fasit: 0, 3, 4, 5, 0.
(display "if tester:") (newline)
(mc-eval '(if (= 3 4) then 3 else 0) the-global-environment)
(mc-eval '(if (= 3 3) then 3 elsif (= 4 4) then 4 else 0) the-global-environment)
(mc-eval '(if (= 3 4) then 3 elsif (= 4 4) then 4 else 0) the-global-environment)
(mc-eval '(if (= 3 4) then 3 elsif (= 4 5) then 4  elsif (= 5 5) then 5 else 0) the-global-environment)
(mc-eval '(if (= 3 4) then 3 elsif (= 4 5) then 4  elsif (= 5 6) then 5 else 0) the-global-environment)


(newline)
;; 3c)

(display "3c)") (newline)

(define (eval-special-form exp env)
  (cond ((quoted? exp) (text-of-quotation exp))
        ((assignment? exp) (eval-assignment exp env))
        ((definition? exp) (eval-definition exp env))
        ((if? exp) (eval-if exp env))
        ((and? exp) (mc-eval (m-and exp env) env))        
        ((or? exp) (mc-eval (m-or exp env) env))        
        ((let? exp) (eval-let exp env))               ;;let
        ((lambda? exp)
         (make-procedure (lambda-parameters exp)
                         (lambda-body exp)
                         env))
        ((begin? exp) 
         (eval-sequence (begin-actions exp) env))
        ((cond? exp) (mc-eval (cond->if exp) env))))

(define (special-form? exp)
  (cond ((quoted? exp) #t)
        ((assignment? exp) #t)
        ((definition? exp) #t)
        ((if? exp) #t)
        ((and? exp) #t)
        ((or? exp) #t)
        ((let? exp) #t)   ;; lagt til let
        ((lambda? exp) #t)
        ((begin? exp) #t)
        ((cond? exp) #t)
        (else #f)))

;; Hjelpemetode for special-form biten
(define (let? exp) (tagged-list? exp 'let))

;; Plukker ut <var>'er i en liste
(define (var-s exp)
  (map car (cadr exp)))

;; Plukker ut <exp>'er i en liste
(define (exp-s exp)
  (map cadr (cadr exp)))

;; Plukker ut <body>
(define (body exp)
  (cddr exp))

;; Let i meta-schemen
(define (eval-let exp env)
  (mc-eval (append (list (make-lambda (var-s exp) (body exp))) (exp-s exp)) env))

(set! the-global-environment (setup-environment))


;; Test av let
;; obs, bruker if som modifisert fra 3b siden den blir evaluert i meta-scheme.

(mc-eval '(let ((nr1 1) (nr2 2)) (if (= nr1 1) then #t else #f)) the-global-environment)
(mc-eval '(let ((nr1 1) (nr2 2)) (if (= nr1 2) then #t else #f)) the-global-environment)
(mc-eval '(let ((nr1 1) (nr2 2) (nr3 3)) (* nr2 nr3)) the-global-environment)
(mc-eval '(let ((nr1 1) (nr2 2) (nr3 3)) (cons nr1 (cons nr2 (cons nr3 nil)))) the-global-environment)

(newline)
;; 3d)

(display "3d)") (newline)

(define (add-var exp)
  (if (eq? (car exp) 'in)
      '()
      (cons (cadr exp) (add-var (cddddr exp)))))

(define (add-exp exp)
  (if (eq? (car exp) 'in)
      '()
      (cons (cadddr exp) (add-exp (cddddr exp)))))

(define (get-body exp)
  (if (eq? (car exp) 'in)
      (list (cadr exp))
      (get-body (cdr exp))))

      
(define (eval-let exp env)
  (mc-eval (append (list (make-lambda (add-var exp) (get-body exp))) (add-exp exp)) env))

(set! the-global-environment (setup-environment))

;; Skjønner ikke helt hvordan den skal ta display og + etterhverandre, får bare til en av gangen...

(mc-eval '(let x = 2 and y = 3 in (display (cons x y)))the-global-environment)
(mc-eval '(let x = 2 and y = 3 in (+ x y)) the-global-environment)
;;(read-eval-print-loop)

