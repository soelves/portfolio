class Sykdom:
    def __init__(self, navn):
        self._navn = navn
        self._posisjoner = []
        #self._antallTreff = 0

    def leggTilPosisjon(self, posisjon):
        self._posisjoner.append(posisjon)

    def erAssosiert(self, posisjon):
        return posisjon in self._posisjoner

class SykdomsKatalog:
    def __init__(self, filnavn):
        self._sykdommer = {}
        self._lesFil(filnavn)

    def _lesFil(self, filnavn):
        fil = open(filnavn)

        for line in fil:
            biter = line.splitt(',')
            posisjon = int(biter[0])
            sykdomsnavn = biter[1].strip() #For å unngå linjeskift

            if sykdomsnavn in self._sykdommer:
                sykdom = self._sykdommer[sykdomsnavn]
            else:
                sykdom = Sykdom(sykdomsnavn)
                self._sykdommer[sykdomsnavn] = sykdom

            sykdom.leggTilPosisjon(posisjon)

    def sjekkMutasjon(self, posisjon):
        for sykdomsnavn in self._sykdommer:
            sykdom = self._sykdommer[sykdomsnavn]
            if sykdom.erAssosiert(posisjon):
                print("Posisjon", posisjon, "er assosiert med", sykdomsnavn)

class Pasient:
    def __init__(self, filnavn):
        self._mutasjoner = []
        self._lesFil(filnavn)

    def _lesFil(self, filnavn):
        fil = open(filnavn)
        for linje in fil:
            posisjon = int(linje)
            self._mutasjoner.append(posisjon)

def testSykdom():
    s = Sykdom("Kolera")

    s.leggTilPosisjon(25)
    s.leggTilPosisjon(30)

    assert s.erAssosiert(30) == True
    assert s.erAssosiert(13) == False

def hovedprogram():
    katalog = SykdomsKatalog('sykdommer_test.csv')
    pasient = Pasient('mutasjoner.txt')
    for posisjon in pasient.alleMutasjoner():
        katalog.sjekkMutasjon(posisjon)

hovedprogram()
