class BlåResept extends Resept{

    public BlåResept(Legemiddel l, Lege uL, Pasient p, int r){
        super(l, uL, p, r);
        pris = l.hentPris();
        pris = (pris*0.25);
    }

    @Override
    public String farge(){
        return "Blaa";
    }

    @Override
    public double prisAaBetale(){
        return pris;
        //return l.hentPris()*0.25;
    }

    @Override
    public String toString() {
        return reseptId + " : Legemiddel: |" + legemiddel + "| Lege: |"
        + utskrivendeLege + "| Pasient: |" + pasient + "| Uttak: "
        + reit + ", Pris å betale: " + pris + "kr.";
    }
}
