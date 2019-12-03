print("Skriv inn noen tall. Skriv inn 0 når du er ferdig.")
tall = 1
tomListe = []

while tall > 0:
    tall=float(input("-> "))
    tomListe.append(tall)

#print(tomListe)

for tall in tomListe:
    print(tall)

minSum = 0

for tall in tomListe:
    minSum += tall

print("Sum:", minSum)

minst = tomListe[0]
for x in tomListe:
    if x < minst and x > 0:
        minst = x
print("Det minste tallet i listen:", minst)

stoerst = tomListe[0]
for y in tomListe:
    if y > stoerst:
        stoerst = y
print("Det største tallet i listen:", stoerst)
