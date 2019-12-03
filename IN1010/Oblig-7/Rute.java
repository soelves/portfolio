abstract class Rute {
    int rad;
    int kolonne;

    Rute nord;
    Rute soer;
    Rute vest;
    Rute oest;
    int antallNaboer = 0;

    String vei = "";

    Labyrint labyrint;

    public Rute(int r, int k){
        rad = r;
        kolonne = k;
    }

    public void settLabyrint(Labyrint l){
        this.labyrint = l;
    }

    public String harJegEnLabyrint(){
        if(labyrint != null){
            return "Ja, du har en labyrint!";
        } else {return "Nei!";}
    }

    public int hentAntallNaboer(){
        return antallNaboer;
    }

    public void settNaboer(Rute[][] ruter, int r, int k){
        r--;
        k--;

        if(rad > 0){nord = ruter[rad-1][kolonne]; antallNaboer++;}
        if(rad < r){soer = ruter[rad+1][kolonne]; antallNaboer++;}
        if(kolonne > 0){vest = ruter[rad][kolonne-1]; antallNaboer++;}
        if(kolonne < k){oest = ruter[rad][kolonne+1]; antallNaboer++;}
    }

    public boolean erJegEnAapning(){
        return false;
    }

    abstract char tilTegn();

    @Override
    public String toString(){
        return "!";
    }

    public void gaa(Rute forrige, String vei){

        if(nord.equals(forrige) == false){
            nord.gaa(this, vei);
        }
        if(soer.equals(forrige) == false){
            soer.gaa(this, vei);
        }
        if(vest.equals(forrige) == false){
            vest.gaa(this, vei);
        }
        if(oest.equals(forrige) == false){
            oest.gaa(this, vei);
        }


    }

    public void finnUtvei(){
        this.gaa(this, vei);
    }

}
