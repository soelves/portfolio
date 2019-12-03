abstract class Resept{

    public static int reseptTeller = -1;
    public int reseptId;
    public double pris;

    public Legemiddel legemiddel;
    public Lege utskrivendeLege;
    public Pasient pasient;
    public int reit;

    public Resept(Legemiddel l, Lege uL, Pasient p, int r){
        legemiddel = l;
        utskrivendeLege = uL;
        pasient = p;
        reit = r;
        reseptTeller++;
        reseptId = reseptTeller;
        pasient.leggTil(this);
        utskrivendeLege.leggTilResept(this);
    }

    public int hentId(){
        return reseptId;
    }

    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }

    public Lege hentLege(){
        return utskrivendeLege;
    }

    public Pasient hentPasient(){
        return pasient;
    }

    public int hentReit(){
        return reit;
    }

    public boolean bruk(){
        if(reit > 0){
            return true;
        }else{
            return false;
        }
    }

    public int taReit(){
        return --reit;
    }

    public boolean erMilitær() {
        return false;
    }

    abstract public String farge();

    abstract public double prisAaBetale();


}
