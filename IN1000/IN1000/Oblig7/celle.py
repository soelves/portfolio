class Celle:

#I denne klassen sin konstruktør vil jeg sette statusen til doed, så da
#kjoerer jeg metoden settDoed i konstruktøren.
    def __init__(self):
        self.settDoed()

#Denne brukte jeg for å sjekke listene mine underveis på en ryddigere måte.
    def __repr__(self):
        return "C"

#Her setter jeg en celles status til doed.
    def settDoed(self):
        self._status = "Doed"

#Her setter jeg en celles status til levende.
    def settLevende(self):
        self._status = "Levende"

#Her sjekker jeg om en celle lever eller ikke.
    def erLevende(self):
        if self._status == "Levende":
            return True
        else:
            return False

#Her gir jeg en celle det tegnet som representerer dens status.
    def hentStatusTegn(self):
        if self._status == "Levende":
            return "O"
        else:
            return "."
