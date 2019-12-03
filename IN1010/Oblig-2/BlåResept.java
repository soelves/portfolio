class BlåResept extends Resept{

    public BlåResept(Legemiddel l, Lege uL, int pI, int r){
        super(l, uL, pI, r);
        pris = l.hentPris();
        pris = (pris*0.25);
        reseptTeller++;
        reseptId = reseptTeller;
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
}
