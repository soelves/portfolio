class Fastlege extends Lege implements Kommuneavtale{

    public Fastlege(String n, int a){
        super(n, a);
    }

    @Override
    public int hentAvtalenummer(){
        return avtalenummer;
    }

}
