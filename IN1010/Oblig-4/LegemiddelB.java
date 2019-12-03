class LegemiddelB extends Legemiddel{

    private int styrke;

    public LegemiddelB(String n, double p, double v,
    int s){
        super(n, p, v);
        styrke = s;
        legemiddelTeller++;
        legemiddelId = legemiddelTeller;
    }

    @Override
    public int hentVanedannendeStyrke(){
        return styrke;
    }

    @Override
    public String toString() {
        return legemiddelId + " : " + navn + ", " + pris + "kr, "
        + virkestoff + " virkestoff, " + styrke + " vanedannende styrke.";
    }

    @Override
    public boolean erVanedannende(){
        return true;
    }

}