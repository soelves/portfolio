class Motorsykkel:

    def __init__(self, merke, regnr, km):
        self._merke = merke
        self._regnr = regnr
        self._km = km

    def kjor(self, km):
        self._km += km

    def hentKilometerstand(self):
        return self._km

    def skrivUt(self):
        print(self._merke)
        print(self._regnr)
        print(self._km)
