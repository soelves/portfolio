class Presepter extends HvitResept{

    public Presepter(Legemiddel l, Lege uL, Pasient p, int r){
        super(l, uL, p, r);
        pris = l.hentPris();
        reit = 3;

        if(pris <= 116){
            pris = 0;
        }else{
            pris -= 116;
        }
    }

    @Override
    public double prisAaBetale(){
        return pris;
    }

    @Override
    public String toString() {
        return reseptId + " : Legemiddel: |" + legemiddel + "| Lege: |"
        + utskrivendeLege + "| Pasient: |" + pasient + "| Uttak: " + reit
        + ", Pris å betale: " + pris + "kr.";
    }
}
