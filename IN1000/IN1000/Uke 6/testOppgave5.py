from oppgave5 import Spill

def hovedprogram():
    nyBruker = Spill(1, 0)
    casual = Spill(2, 5)
    legend = Spill(3, 12)

    print(nyBruker.visLvl())
    nyBruker.quest()
    print(nyBruker.visLvl())
    nyBruker.quest()
    print(nyBruker.visLvl())

    casual.quest()
    casual.gameOver()
    casual.quest()
    casual.boss()
    print(casual.visPoeng())
    print(casual.visLvl())

    legend.boss()
    print(legend.visPoeng())
    print(legend.visLvl())
    
hovedprogram()
