from spillebrett import Spillebrett
from celle import Celle
def hovedprogram():


    '''celle1 = Celle()
    print(celle1.hentStatusTegn())
    celle1.settLevende()
    print(celle1.hentStatusTegn())'''

    celle1 = Celle()

    etBrett = Spillebrett(20,20)

    etBrett.tegnBrett()
    etBrett.finnAntallLevende()
    etBrett.oppdatering()
    etBrett.finnAntallLevende()

hovedprogram()
