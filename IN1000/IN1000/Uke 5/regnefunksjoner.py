def addisjon(a,b):
    return a + b

def subtraksjon(a,b):
    return a - b

def divisjon(a,b):
    return a / b

def tommerTilCm(antallTommer):
    assert antallTommer > 0
    return antallTommer * 2.54

def skrivBeregninger():
    a = float(input("Skriv inn et tall: "))
    b = float(input("Skriv inn et tall til: "))

    print("Resultat av summering:", addisjon(a,b))
    print("Resultat av subtraksjon:", subtraksjon(a,b))
    print("Resultat av divisjon:", divisjon(a,b))

    antallTommer = float(input(("Konvertering til tommer fra cm:\nSkriv inn en lengde: ")))
    print("Resultat:", tommerTilCm(antallTommer))

def hovedprogram():
    #Under er en test av funksjonen addisjon, som skal addere to parametre.
    print(addisjon(8,7))

    #Test av funksjonen subtraksjon.
    assert subtraksjon(8,7) == 1
    assert subtraksjon(-8,-7) == -1
    assert subtraksjon (-8,7) == -15

    #Test av funksjonen divisjon.
    assert divisjon(4,2) == 2
    assert divisjon(-4,-2) == 2
    assert divisjon(-4,2) == -2

    #Test av funskjonen tommerTilCm.
    print(tommerTilCm(10))

    skrivBeregninger()

hovedprogram()
