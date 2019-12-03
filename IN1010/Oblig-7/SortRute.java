class SortRute extends Rute {

    public SortRute(int r, int k){
        super(r, k);
    }

    @Override
    public char tilTegn(){
        return '#';
    }

    @Override
    public String toString(){
        return "#";
    }

    @Override
    public void gaa(Rute forrige, String vei){

    }
}
