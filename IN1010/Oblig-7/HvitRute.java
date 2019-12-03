class HvitRute extends Rute {

    public HvitRute(int r, int k){
        super(r, k);
    }

    @Override
    public char tilTegn(){
        return '.';
    }

    @Override
    public String toString(){
        return ".";
    }

    @Override
    public void gaa(Rute forrige, String vei){

        vei = vei + "(" + kolonne + "," + rad + ") --> ";

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
}
