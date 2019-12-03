class Aapning extends HvitRute {
    public Aapning(int r, int k){
        super(r, k);
    }

    @Override
    public boolean erJegEnAapning(){
        return true;
    }

    @Override
    public void gaa(Rute forrige, String vei){


        vei = vei + "(" + kolonne + "," + rad + ")";

        labyrint.veier.leggTil(vei);

        /*if(nord.equals(forrige) == false){
            nord.gaa(this);
        }
        if(soer.equals(forrige) == false){
            soer.gaa(this);
        }
        if(vest.equals(forrige) == false){
            vest.gaa(this);
        }
        if(oest.equals(forrige) == false){
            oest.gaa(this);
        }*/
    }
}
