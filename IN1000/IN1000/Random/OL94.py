#Vi vet hvor mange medaljer som blir utdelt for hver gren.
#Vi vet ikke hvor mange grener, eller deltakere.


class OL:
    def __init__(self, grener):
        self._grener = grener   #Heltall: Antall grener
        self._konkurranser = []


    def lagreVinnere(self):
        self:_vinnere = []      #Alle med medalje
        self._tapere = []       #Alle uten medalje

        for konkurranse in konkurranser:
            for deltaker in konkurranse:
                if deltaker.harMedalje():
                    vinnere.append(deltakere)
                else:
                    tapere.append(deltakere)
