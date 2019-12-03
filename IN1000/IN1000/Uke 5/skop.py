#Først defineres funksjonen minFunksjon, som ikke tar imot noen parametere.

def minFunksjon():
    for x in range(2):
        c = 2
        print(c)
        c += 1
        b = 10
        b += a
        print(b)
    return b

#Deretter defineres prosedyren hovedprogram, som ikke tar imot noen parametere.

def hovedprogram():
    a = 42
    b = 0
    print(b)
    b = a
    a = minFunksjon()
    print(a)
    print(b)

#Her kalles hovedprogrammet.
hovedprogram()

#Inne i hovedprogrammet oprettes variablene a = 42, og b = 0.
#b blir printet, og b blir nå gjort om til a, 42.
#Så kaller vi på minFunksjon, hvor en for løkke blir kjørt 2 ganger.
#Variablen c = 2, og c blir printet ut. Deretter blir c addert med 1.
#Variabelen b i minFunksjon blir satt til 10, og blir addert med variablen a.
#Variablen a har ingen verdi, den er kun satt i skopet til hovedprogram.
#Her vil da programmet slutte å fungere.
