from noder import Node

class Rack:
    def __init__(self):
        self._rack = []

    def settInn(self, node):
        self._rack.append(node)

    def getAntNoder(self):
        return len(self._rack)

    def antProsessorer(self):
        sumProsessorer = 0
        for i in range(0, len(self._rack)):
            sumProsessorer += self._rack[i].antProsessorer()
        return sumProsessorer

    def noderMedNokMinne(self, paakrevdMinne):
        antallNoder = 0
        for i in range(0, len(self._rack)):
            if self._rack[i].nokMinne(paakrevdMinne) == True:
                antallNoder += 1
        return antallNoder
