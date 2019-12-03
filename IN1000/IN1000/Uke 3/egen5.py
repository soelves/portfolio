#Skriv et program som tar imot koordinater, høyde og bredde, og legger dem i en
#liste. Bruk innholdet i listen til å tegne en form.

from ezgraphics import GraphicsWindow

Vindu = GraphicsWindow(500, 500)
Lerret = Vindu.canvas()
Lerret.setColor("Cyan")

Liste = []

Liste.append(float(input("Skriv inn et x-koordinat: ")))
Liste.append(float(input("Skriv inn et y-koordinat: ")))
Liste.append(float(input("Skriv inn høyden: ")))
Liste.append(float(input("Skriv inn bredden: ")))
print(Liste)
Lerret.drawOval(Liste[0],Liste[1],Liste[2],Liste[3])

Vindu.wait()
