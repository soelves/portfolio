class Militærresepter extends HvitResept{

    public Militærresepter(Legemiddel l, Lege uL, Pasient p, int r){
        super(l, uL, p, r);
        pris = 0;
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

    @Override
    public boolean erMilitær() {
        return true;
    }
}
