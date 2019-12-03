from rack import Rack

class Regneklynge:
    def __init__(self, noderPerRack):
        self._klynge = []
        self._noderPerRack = noderPerRack

    def settInnNode(self, node):
        added = False

        if len(self._klynge) < 1:
            etRack = Rack()
            etRack.settInn(node)
            self._klynge.append(etRack)
        else:
            for rack in self._klynge:
                if rack.getAntNoder() < self._noderPerRack:
                    rack.settInn(node)
                    added = True
            if added == False:
                etRack = Rack()
                etRack.settInn(node)
                self._klynge.append(etRack)


    def antProsessorer(self):
        sumProsessorer = 0
        for i in range(0, len(self._klynge)):
            sumProsessorer += self._klynge[i].antProsessorer()
        return sumProsessorer

    def noderMedNokMinne(self, paakrevdMinne):
        antallNoder = 0
        for i in range(0, len(self._klynge)):
            antallNoder += self._klynge[i].noderMedNokMinne(paakrevdMinne)
        return antallNoder

    def antRacks(self):
        return len(self._klynge)
