from random import randint
from celle import Celle

class SpillebrettV2:

#I konstruktoeren velger jeg stoerrelsen paa brettet, lager et rutenett, setter
#generasjonsnummeret, putter celler i rutenettet med preGenerer, og til slutt
#genererer et tilfeldig spillebrett med generer.
    def __init__(self, rader, kolonner):
        self._rader = rader
        self._kolonner = kolonner
        self._rutenett = []
        self._generasjonsnummer = 0
        self.preGenerer()
        self.generer()


#I preGenerer lager jeg en liste for hver rad, som jeg fyller med celler for
#lengden av kolonnene.
    def preGenerer(self):
        for i in range (self._rader):
            liste = []
            self._rutenett.append(liste)
            for j in range (self._kolonner):
                enCelle = Celle()
                liste.append(enCelle)


#I tegnBrett gaar jeg gjennom rutenettet jeg har fylt og sjekker hver celle sin
#status. Saa bruker jeg hentStatusTegn til aa gi hver celle et tegn ut ifra
#dette, for saa og printe ut cellen.
    def tegnBrett(self):
        for i in range (self._rader):
            for j in range (self._kolonner):
                print(self._rutenett[i][j].hentStatusTegn(), end = "")
            print()


    def oppdatering(self):
#I oppdatering lager jeg foerst to lister, hvor cellene skal bli lagt foer de
#skifter status til neste generasjon. Generasjonsnummeret blir oppdatert.
        lev = []
        drep = []
        self._generasjonsnummer += 1

#Her gaar jeg gjennom listen med naboer for hver celle, som er levende og doede,
#for saa aa lage en ny liste med de naboene som lever, antallNaboer.
        for i in range (self._rader):
            for j in range (self._kolonner):
                enCelle = self._rutenett[i][j]
                antallNaboer = []
                for nabo in self.finnNabo(i,j):
                    if nabo.erLevende() == True:
                        antallNaboer.append(nabo)

#Her bruker jeg denne listen med levende naboer for aa se om de ulike cellene
#skal doe eller leve.
                if self._rutenett[i][j].erLevende() == True:
                    if len(antallNaboer) in [0,1,4,5,6,7,8]:
                        drep.append(enCelle)
                    else:
                        lev.append(enCelle)
                else:
                    if len(antallNaboer) == 3:
                        lev.append(enCelle)
                    else:
                        drep.append(enCelle)

#Her gaar jeg gjennom de to listene, og setter statusen til levende i lev,
#og doed i drep.
        for k in range (0, len(lev)):
                lev[k].settLevende()

        for l in range (0, len(drep)):
                drep[l].settDoed()


#I finnAntallLevende gaar jeg gjennom alle cellene og sjekker om de lever eller
#ikke. Hvis de lever, saa oeker telleren med 1. Til slutt returneres tellern.
    def finnAntallLevende(self):
        teller = 0
        for i in range (self._rader):
            for j in range (self._kolonner):
                enCelle = self._rutenett[i][j]
                if enCelle.erLevende() == True:
                    teller += 1
        return teller


#I generer settes hver celle til levende med en sannsylighet på 25%.
    def generer(self):
        for i in range (self._rader):
            for j in range (self._kolonner):
                rand = randint(0,3)
                if rand == 3:
                    self._rutenett[i][j].settLevende()


#I finnNabo blir alle cellenes omringede celler lagt til i en liste som
#blir returnert.
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
