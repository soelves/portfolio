#Lag et program hvor bruker svarer på spørmsål, i et gameshow format.
#Svarer brukeren riktig, går den til neste steg.
#Svarer brukeren feil, blir ikke brukeren stilt flere spørsmål.

sp0 = input("Velkommen til Sølve's Gameshow. Er du klar? ")
if sp0 == "Ja":
    sp1 = input("Da starter vi! Hva er hovedstaden i Norge? ")
    if sp1 == "Oslo":
        sp2 = int(input("Riktig! Hvor mange dager er det i Februar? "))
        if sp2 == 28:
            sp3 = input("Riktig! Siste spørmsål: Hvilket norskt fylke finner du byen Porsgrunn? ")
            if sp3 == "Telemark":
                print("Riktig! Du er jammen meg flink, du svarte rett på alle tre spørsmålene!")
            else:
                print("Beklager, det var feil. Bedre lykke neste gang!")
        else:
            print("Beklager, det var feil. Bedre lykke neste gang!")
    else:
        print("Beklager, det var feil. Bedre lykke neste gang!")
else:
    print("Okei, da får jeg vente til noen har lyst...")
