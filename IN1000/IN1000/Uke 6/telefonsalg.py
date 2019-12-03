def innlesning(filnavn):
    statistikk = {}
    for innlest in open(filnavn,"r"):
        biter = innlest.split()
        navn = biter[0]
        salg = int(biter[1])
        statistikk[navn] = salg
    return statistikk

def maanedensSalgsperson(ordbok):
    maks = 0
    for person in ordbok:
        if ordbok[person] > maks:
            maks = ordbok[person]
            navn = person
    print("Maanedens ansatte er", navn, "med", maks, "salg.\n")

def totaltAntallSalg(ordbok):
    Sum = 0
    for person in ordbok:
        Sum += ordbok[person]
    return Sum

def gjennomsnittSalg(ordbok):
    return totaltAntallSalg(ordbok)/len(ordbok)

def hovedprogram():
    ordbok = innlesning("salgstall.txt")
    maanedensSalgsperson(ordbok)
    print("Totalt antall salg:", totaltAntallSalg(ordbok))
    print("Aktive selgere denne maaneden:", len(ordbok))
    print("Gjennomsnittlig antall salg per salgsperson:",
    gjennomsnittSalg(ordbok))

hovedprogram()
