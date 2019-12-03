from hund import Hund

def hovedprogram():
    fido = Hund(4,20)

    print("Fido er", fido.hentAlder(), "aar gammel.")

    fido.spring()
    fido.spring()
    fido.spring()
    fido.spring()
    fido.spring()
    print(fido.hentVekt())
    fido.spring()
    print(fido.hentVekt())

    fido.spis(1)
    print(fido.hentVekt())
    fido.spis(5)
    print(fido.hentVekt())

hovedprogram()
