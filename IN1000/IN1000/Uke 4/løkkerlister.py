#Lag et program som systematiserer dine tre beste venners bursdager.
#For så å kunne sjekke opp disse bursdagene til navnet på vennene dine.

input("La oss legge inn bursdagene til de 3 beste vennene dine! Klar? ")
antall = [0]*3
venner = []
bursdag = []

for folk in antall:
    venner.append(input("Hva heter han/hun? "))
    bursdag.append(input("Når har han/hun bursdag? "))

sjekk = 1

while sjekk > 0 and sjekk < 2:
    navn = input("Hvem vil du vite bursdagen til? ")
    if navn == venner[0]:
        print(bursdag[0])
    elif navn == venner[1]:
        print(bursdag[1])
    elif navn == venner[2]:
        print(bursdag[2])
    else:
        print("Sorry, det skjønte jeg ikke...")
    sjekk = int(input("Vil du fortsette, tast inn 1. "))
