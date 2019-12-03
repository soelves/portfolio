class HvitResept extends Resept{

    public HvitResept(Legemiddel l, Lege uL, Pasient p, int r){
        super(l, uL, p, r);
        pris = l.hentPris();
    }

    @Override
    public String farge(){
        return "Hvit";
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
