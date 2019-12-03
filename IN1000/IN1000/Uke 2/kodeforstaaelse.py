#1. Nei, dette er ikke lovlig kode, fordi "+" skal være byttet ut med ",".
#   Vi kan ikke plusse heltall og tekst.
#2. Programmet vil stoppe på linje 8, pga. "+".

a=input("Tast inn et heltall! ")
b=int(a)
if b < 10:
    print(b + "Hei!")
