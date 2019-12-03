def slaaSammen(x,y):
    return x + y

def skrivUt(liste):
    for i in range(len(liste)):
        print(liste[i])


def hovedprogram():
    mineOrd = []
    a = "i"
    while a != "s":
        a = input("i for input, u for utskrift, s for avslutt: ")
        if a == "i":
            streng1 = input("Skriv inn en tekststreng:\n")
            streng2 = input("Skriv inn en tekststreng:\n")
            mineOrd.append(slaaSammen(streng1,streng2))
            print(mineOrd)
        elif a == "u":
            skrivUt(mineOrd)
        elif a == "s":
            print("Programmet avsluttes.")
        else:
            print("Feil input!")

hovedprogram()
