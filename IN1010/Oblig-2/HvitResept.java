abstract class HvitResept extends Resept{

    public HvitResept(Legemiddel l, Lege uL, int pI, int r){
        super(l, uL, pI, r);
    }

    @Override
    public String farge(){
        return "Hvit";
    }
}
