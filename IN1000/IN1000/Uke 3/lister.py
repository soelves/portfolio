MineTall = [2,5,7]
MineTall.append(1)

print(MineTall[0])
print(MineTall[2])

Navn = []
Navn.append(input("Oppgi navn 1 av 4: "))
Navn.append(input("Oppgi navn 2 av 4: "))
Navn.append(input("Oppgi navn 3 av 4: "))
Navn.append(input("Oppgi navn 4 av 4: "))

#print(Navn)

if "Soelve" in Navn:
    print("Du husket meg!")
else:
    print("Glemte du meg?")

NyListe = MineTall + Navn
print(NyListe)

NyListe.pop(7)
NyListe.pop(6)
print(NyListe)
