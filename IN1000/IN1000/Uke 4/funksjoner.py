
def adder(tall1, tall2):
    return tall1 + tall2

print(adder(5, 7), "er summen av 5 og 7")
print(adder(3, 4), "er summen av 3 og 4")

#tekst = input("Skriv inn litt tekst: ")
#bokstav = input("Skriv inn en bokstav: ")

#print(tekst.count(bokstav))

def tellForekomst(minTekst, minBokstav):
    return minTekst.count(minBokstav)

print(tellForekomst(input("Skriv inn litt tekst: "), input("Og en bokstav: ")))
