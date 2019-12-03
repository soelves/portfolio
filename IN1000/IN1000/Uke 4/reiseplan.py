steder = []
for reise in range(0, 5):
    steder.append(input("Skriv inn et reisemål: "))
#print(steder)

klesplagg = []
for klær in range(0, 5):
    klesplagg.append(input("Skriv inn et klesplagg: "))

avreisedatoer = []
for avreise in range(0, 5):
    avreisedatoer.append(input("Skriv inn en avreisedato: "))

reiseplan = [steder, klesplagg, avreisedatoer]

for i in range(1):
    print(reiseplan)

#i1 = reiseplan[int(input("Vil du nå liste 1, 2 eller 3? ")) - 1]
#print(i1)
i1 = int(input("Vil du nå liste 1,2 eller 3?" )) - 1

#i2 = i1[int(input("Vil du ha gjenstand 1, 2, 3, 4, eller 5? ")) - 1]
#print(i2)
i2 = int(input("Vil du ha gjenstand 1, 2, 3, 4, eller 5? ")) - 1

if -1 < i1 < 3 and -1 < i2 < 5:
    print(reiseplan[i1][i2])
else:
    print("Ugyldig input!")
