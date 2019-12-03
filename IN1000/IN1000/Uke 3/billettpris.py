def Gammel() :
    alder = int(input("Hvor gammel er du? "))
    billettpris=0
    if alder <= 17:
        billettpris=30
        print(billettpris, "kr for billett!")
    elif alder > 17 and alder < 63:
        billettpris=50
        print(billettpris, "kr for billett!")
    else:
        billettpris=35
        print(billettpris, "kr for billett!")

Gammel()

Gammel()

Gammel()

Gammel()

#Jeg ser ikke noe problem med prosedyren slik den er nå...
