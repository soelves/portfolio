class Legemiddel{

    public static int legemiddelTeller = -1;
    public int legemiddelId;
    public String navn;
    public double pris;
    public double virkestoff;
    public int styrke;

    public Legemiddel(String n, double p, double v){
        navn = n;
        pris = p;
        virkestoff = v;
    }

    public int hentId(){
        return legemiddelId;
    }

    public String hentNavn(){
        return navn;
    }

    public double hentPris(){
        return pris;
    }

    public double hentVirkestoff(){
        return virkestoff;
    }

    public void settNyPris(int nyPris){
        pris = nyPris;
    }

    public int hentNarkotiskStyrke(){
        return styrke;
    }

    public int hentVanedannendeStyrke(){
        return styrke;
    }
}
