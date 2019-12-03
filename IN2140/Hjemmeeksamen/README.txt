For å kjøre programmet:

make all
make run NR="script" BASEPORT="baseport"

"script" er nummeret på scriptet du vil kjøre. Vi ble gitt to, 1 og 2.
"baseport" er baseporten du vil bruke.

Eks:
make run NR=1 BASEPORT=21400

make clean fjerner ikke loggene. (Ble nevnt på plenumsforelesning).

-----
Var litt usikker på hva som var ment med:
"make run ​- kjører begge skriptene som vi har gitt"
Om det skulle kjøre begge etterhverandre eller hver for seg.
Jeg antok hver for seg, håper det går bra.
-----

Kjente problemer:
Jeg prater ikke over TCP i løkke. Jeg rakk det ikke/fikk det ikke til.
Siden forsendelsene er såppas små, er det sjeldent et problem, men det har
skjedd et par ganger at programmet stopper pga. at det ikke blir lest nok info.

Noen ganger henger programmet når jeg bruker valgrind, litt usikker på
hvorfor, er ikke så god på valgrind, men hjelper som oftest å bytte baseport.

-----

Vet at jeg har en del warnings, og at det ikke er perfekt, men er veldig
fornøyd med at jeg klarte å levere et produkt som gjør det oppgaven ba om.
Synes kurset er vanskelig,  og har brukt mye tid, så veldig glad for at jeg
fikk til det aller meste av oppgaven.
