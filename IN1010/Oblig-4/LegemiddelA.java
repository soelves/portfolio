class LegemiddelA extends Legemiddel{

    public LegemiddelA(String n, double p, double v,
    int s){
        super(n, p, v);
        styrke = s;
        legemiddelTeller++;
        legemiddelId = legemiddelTeller;
    }

    @Override
    public int hentNarkotiskStyrke(){
        return styrke;
    }

    @Override
    public String toString() {
        return legemiddelId + " : " + navn + ", " + pris + "kr, "
        + virkestoff + " virkestoff, " + styrke + " narkotisk styrke.";
    }

    @Override
    public boolean erNarkotisk(){
        return true;
    }
}
