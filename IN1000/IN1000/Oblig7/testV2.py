from spillebrettV2 import SpillebrettV2
from celle import Celle
def hovedprogram():
    size = int(input("Hvor stort vil du ha spillebrettet?\n"))

    enCelle = Celle()
    etBrett = SpillebrettV2 (size, size)
    avslutt = ""
    print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
    etBrett.tegnBrett()
    print("Generasjon:", etBrett._generasjonsnummer)
    print("Antall levende celler:", etBrett.finnAntallLevende())
    avslutt = input("Fortsett? Trykk ENTER  ◕  Avslutt? Trykk q + ENTER:\n")

    while avslutt != "q":
        etBrett.oppdatering()
        print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
        etBrett.tegnBrett()
        print("Generasjon:", etBrett._generasjonsnummer)
        print("Antall levende celler:", etBrett.finnAntallLevende())
        avslutt = input("Fortsett? Trykk ENTER  ◕  Avslutt? Trykk q + ENTER:\n")

hovedprogram()
