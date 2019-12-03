def snitt(liste):
    Sum = 0
    for verdi in liste:
        Sum += verdi
    gjennomsnitt = Sum/len(liste)
    print("Gjennomsnitt:", gjennomsnitt)

def hovedprogram():
    temp = open("temperatur.txt", "r")
    mnd=[]
    for linje in temp:
        mnd.append(float(linje))

    print(mnd)

    snitt(mnd)

    temp.close()

hovedprogram()
