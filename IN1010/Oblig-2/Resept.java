abstract class Resept{

    public static int reseptTeller = -1;
    public int reseptId;
    public double pris;

    public Legemiddel legemiddel;
    public Lege utskrivendeLege;
    public int pasientId;
    public int reit;

    public Resept(Legemiddel l, Lege uL, int pI, int r){
        legemiddel = l;
        utskrivendeLege = uL;
        pasientId = pI;
        reit = r;
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

    public int hentPasientId(){
        return pasientId;
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

    abstract public String farge();

    abstract public double prisAaBetale();


}
