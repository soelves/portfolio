class Militærresepter extends HvitResept{

    public Militærresepter(Legemiddel l, Lege uL, int pI, int r){
        super(l, uL, pI, r);
        pris = 0;
        reseptTeller++;
        reseptId = reseptTeller;
    }

    @Override
    public double prisAaBetale(){
        return pris;
    }
}
