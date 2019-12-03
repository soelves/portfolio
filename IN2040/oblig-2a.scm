;; Oblig 2a, solvesel

(load "huffman.scm")

;; Oppgave 1
;; a)

(define (p-cons x y)
  (lambda (proc) (proc x y)))

(define (p-car p)
  (p (lambda (x y) x)))

(define (p-cdr p)
  (p (lambda (x y) y)))


(p-cons "foo" "bar")

(p-car (p-cons "foo" "bar"))

(p-cdr (p-cons "foo" "bar"))

(p-car (p-cdr (p-cons "zoo" (p-cons "foo" "bar"))))


;; b)

(define foo 42)

;; 1
;; let fra oppgaveteksten
(let ((foo 5)
      (x foo))
  (if (= x foo)
      'same
      'different))

;; ekvivalent lamnda-uttrykk
((lambda (foo x)
   (if (= x foo)
       'same
       'different))
   5 foo)

;; Utrykkene vil få verdien different, fordi foo sin oppdaterte lokale verdi 5 vil ikke være
;; tilgjengelig for x, da verdien x trekker fra foo er den globale 42.


;; 2
;; let fra oppgaveteksten
(let ((bar foo)
      (baz 'towel))
  (let ((bar (list bar baz))
        (foo baz))
    (list foo bar)))

;; ekvivalent lamnda-uttrykk
((lambda (bar baz)
   ((lambda (bar foo)
      (list foo bar))
    (list bar baz) baz))
   foo 'towel)

#|
Utrykkene vil få verdien (towel (42 towel)). bar blir satt til foo. baz blir
satt til 'towel. bar blir så satt til en liste av sin tidligere verdi, foo, som
er 42, og baz, som er 'towel. (42 towel). Den lokale foo  blir satt til baz, som er 'towel.
Til slutt legges den lokale foo og bar inn i en liste, og en får (towel (42 towel)).

Tror dette er ca. hvordan det foregår, litt innviklet.
|#


;; c)

(define (infix-eval exp)
  (let ((en (car exp))
        (to (car (cdr (cdr exp))))
        (pros (car (cdr exp))))
    (pros en to)))


(define foo (list 21 + 21))
(define baz (list 21 list 21))
(define bar (list 84 / 2))

(infix-eval foo)
(infix-eval baz)
(infix-eval bar)


;; d)

#|
(define bah '(84 / 2))

(infix-eval bah)

Dette vil ikke fungere, siden / ikke lenger blir sett på som en prosedyre,
men et symbol. 
|#

;; 2
;; a)
#|
Det jeg tror er meningen med å ha en intern hjelpeprosedyre i denne situasjonen, er fordi
Vi har lyst til å kunne ha tilgang til det orginale treet vi sender inn, tree, samtidig
som vi har en referanse til hvor vi befinner oss nå, current-branch.

(cons (symbol-leaf next-branch)
                    (decode-1 (cdr bits) tree))

Her er begge referet i en cons, kan se for meg at det kunne blitt komplisert å oppnå
noe lignende uten å ha begge disse referansepunktene.
|#

;; b)
#|
decode fra huffman.scm

(define (decode bits tree)
  (define (decode-1 bits current-branch)
    (if (null? bits)
        '()
        (let ((next-branch
               (choose-branch (car bits) current-branch)))
          (if (leaf? next-branch)
              (cons (symbol-leaf next-branch)
                    (decode-1 (cdr bits) tree))
              (decode-1 (cdr bits) next-branch)))))
  (decode-1 bits tree))
|#

(define (h-decode bits tree)
  (define (h-decode-1 bits current-branch ret-list)
    (if (null? bits)
        (reverse ret-list)
        (let ((next-branch
               (choose-branch (car bits) current-branch)))
          (let ((con-list
               (cons (symbol-leaf next-branch) ret-list)))
                  (if (leaf? next-branch)
                      (h-decode-1 (cdr bits) tree con-list)
              (h-decode-1 (cdr bits) next-branch ret-list))))))  
  (h-decode-1 bits tree '()))


;; c)

;; Kjører orginalen og hale versjon.
(decode sample-code sample-tree)
(h-decode sample-code sample-tree)

#|
Resultatet i orginal decode er (ninjas fight ninjas by night).
I min h-decode blir lista bygget opp baklengs, så jeg leverer den med (reverse),
og ender opp med det samme resultatet som orginal decode.

Litt usikker på om jeg fikk det til godt (oppg. b), men følte hvertfall jeg fikk til litt.
Ideen er hvertfall at jeg fyller opp en liste underveis, istedenfor at det bygges
opp en lang kø av cons kall for å lage lista. Det føltes litt rotete med to let
etterhverandre, er det et bedre alternativ?
|#

;; d)

(define (encode symbols tree)
  (define (encode-1 symbols current-branch)
    (if (null? symbols)
        '()
        (if (leaf? (car current-branch))
            (if (eq? (cadar current-branch) (car symbols))
                (cons 0 (encode-1 (cdr symbols) tree))
                (cons 1 (encode-1 symbols (cdr current-branch))))
            (encode-1 symbols (car current-branch)))))
  (encode-1 symbols tree))

(encode '(ninjas fight ninjas) sample-tree)
(decode (encode '(ninjas fight ninjas) sample-tree) sample-tree)

;; e)

(define freqs '((a 2) (b 5) (c 1) (d 3) (e 1) (f 3)))

(define (grow-huffman-tree freqs)
  (let ((sortert
         (make-leaf-set freqs)))
    (define (add-branch s-freqs)
      (if (null? s-freqs)
          '()
          (if (null? (cdr s-freqs))
              (car s-freqs)
              (make-code-tree (car s-freqs) (add-branch (cdr s-freqs))))))
    (add-branch (reverse sortert))))

(define codebook (grow-huffman-tree freqs))

(decode (encode '(a b c) codebook) codebook)


;; f)

(define ffreqs '((samurais 57) (ninjas 20) (fight 45) (night 12)
                               (hide 3) (in 2) (ambush 2) (defeat 1)
                               (the 5) (sword 4) (by 12) (assassin 1)
                               (river 2) (forest 1) (wait 1) (poison 1)))

(define ffreqs-tree (grow-huffman-tree ffreqs))

(define bit-list(encode '(ninjas fight ninjas fight ninjas fight samurais
                 samurais fight samurais fight ninjas ninjas fight by night) ffreqs-tree))

(length bit-list)

;; 1. Det tar 39 bits å kode meldingen.

;; 2. Gjennomsnittlig lengde på hvert kodeord som brukes i meldingen er 5.7

;; 3. 

;; g)

;; h)
                               

    


              
              
