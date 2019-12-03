#Skriv et program som henter tider fra en fil.
def hovedprogram():
    deltakere = {}
    for linje in open("oppgave5.txt", "r"):
        biter = linje.split()
        navn = biter[0]
        tid = biter[1]
        deltakere[navn] = tid
    hvem = input("Hvilken deltaker?: ")
    personen(hvem)
    print( deltakere[hvem] )

def personen(hvem):
    return "Du valgte", hvem, "som kjoerte paa tiden:"

hovedprogram()
