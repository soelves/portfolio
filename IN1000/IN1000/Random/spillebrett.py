from random import randint
from celle import Celle

class Spillebrett:
    def __init__(self, rader, kolonner):
        self._rader = rader
        self._kolonner = kolonner
        self._rutenett = []
        self._generasjonsnummer = 0
        self.preGenerer()
        self.generer()

    def preGenerer(self):
        for i in range (self._rader):
            liste = []
            self._rutenett.append(liste)
            for j in range (self._kolonner):
                enCelle = Celle()
                liste.append(enCelle)

    def tegnBrett(self):
        for i in range (self._rader):
            for j in range (self._kolonner):
                print(self._rutenett[i][j].hentStatusTegn(), end = "")
            print()

    def oppdatering(self):
        lev = []
        drep = []


        for i in range (self._rader):
            for j in range (self._kolonner):
                enCelle = self._rutenett[i][j]
                if enCelle.erLevende() == True:
                    if len (self.finnNabo(i,j)) < 2:
                        drep.append(enCelle)
                    elif len (self.finnNabo(i,j)) >= 2 and len (self.finnNabo(i,j)) <= 3:
                        lev.append(enCelle)
                    elif len (self.finnNabo(i,j)) > 3:
                        drep.append(enCelle)
                else:
                    if len (self.finnNabo(i,j)) == 3:
                        lev.append(enCelle)
                    else:
                        drep.append(enCelle)

        for k in range (0, len(lev)):
                lev[k].settLevende()

        for l in range (0, len(drep)):
                drep[l].settDoed()

        self._generasjonsnummer += 1



    def finnAntallLevende(self):
        teller = 0
        for i in range (self._rader):
            for j in range (self._kolonner):
                enCelle = self._rutenett[i][j]
                if enCelle.erLevende() == True:
                    teller += 1
        print(teller)

    def generer(self):
        for i in range (self._rader):
            for j in range (self._kolonner):
                rand = randint(0,3)
                if rand == 3:
                    self._rutenett[i][j].settLevende()

    def finnNabo (self, rad, kolonne):
        naboliste = []
        for i in range (-1, 2):
            for j in range (-1, 2):
                naboRad = rad + i
                naboKolonne = kolonne + j
                if (naboRad == rad and naboKolonne == kolonne) != True:
                    if (naboRad < 0 or naboKolonne < 0 or naboRad > self._rader-1 or naboKolonne > self._kolonner-1) != True:
                        naboliste.append(self._rutenett[naboRad][naboKolonne])
        return naboliste
