class LegemiddelC extends Legemiddel{

    public LegemiddelC(String n, double p, double v){
        super(n, p, v);
        legemiddelTeller++;
        legemiddelId = legemiddelTeller;
    }


}
