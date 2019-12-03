#Lag et program som oeker brukeren sitt nivaa ut ifra brukerens poeng.
#Oppgaven har enda en fil, oppgave5test.py
class Spill:
    def __init__(self, lvl, poeng):
        self._lvl = lvl
        self._poeng = poeng

    def visLvl(self):
        return self._lvl

    def visPoeng(self):
        return self._poeng

    def quest(self):
        self._poeng += 2
        if self._lvl < 2:
            if self._poeng >= 4:
                self._lvl += 1
                self._poeng -= 4
        if self._lvl < 3:
            if self._poeng >= 8:
                self._lvl += 1
                self._poeng -= 8
        if self._lvl < 4:
            if self._poeng >= 16:
                self._lvl += 1
                self._poeng -= 16

    def boss(self):
        self._poeng += 4
        if self._lvl < 2:
            if self._poeng >= 4:
                self._lvl += 1
                self._poeng -= 4
        if self._lvl < 3:
            if self._poeng >= 8:
                self._lvl += 1
                self._poeng -= 8
        if self._lvl < 4:
            if self._poeng >= 16:
                self._lvl += 1
                self._poeng -= 16

    def gameOver(self):
        self._poeng = 0
        return self._poeng
