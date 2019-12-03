from motorsykkel import Motorsykkel

def hovedprogram():

    sykkel1 = Motorsykkel("\nKTM", "HH 1964", 1340)
    sykkel2 = Motorsykkel("\nYamaha", "NF 4356", 352)
    sykkel3 = Motorsykkel("\nVespa", "ZZ 1100", 196)

    sykkel1.skrivUt()
    sykkel2.skrivUt()
    sykkel3.skrivUt()

    sykkel3.kjor(10)
    print(sykkel3.hentKilometerstand())

hovedprogram()
