class Presepter extends HvitResept{

    public Presepter(Legemiddel l, Lege uL, int pI, int r){
        super(l, uL, pI, r);
        pris = l.hentPris();
        reit = 3;
        reseptTeller++;
        reseptId = reseptTeller;

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
}
