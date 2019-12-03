from noder import Node
from rack import Rack
from regneklynge import Regneklynge

def hovedprogram():
    abel = Regneklynge(12)

    litenNode = Node(64, 1)

    storNode = Node(1024, 2)

    for i in range(650):
        abel.settInnNode(litenNode)

    for i in range(16):
        abel.settInnNode(storNode)

    print("Noder med minst 32 GB:", abel.noderMedNokMinne(32))
    print("Noder med minst 64 GB:", abel.noderMedNokMinne(64))
    print("Noder med minst 128 GB:", abel.noderMedNokMinne(128), "\n")
    print("Antall prosessorer:", abel.antProsessorer())
    print("Antall rack:", abel.antRacks())

hovedprogram()
