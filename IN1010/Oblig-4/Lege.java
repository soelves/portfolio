class Lege implements Comparable<Lege> {
    public String navn;
    public int avtalenummer;
    Liste<Resept> resepter;

    public Lege(String n, int a){
        navn = n;
        avtalenummer = a;
        resepter = new Lenkeliste<Resept>();
    }

    public String hentNavn(){
        return navn;
    }

    @Override
    public int compareTo(Lege annen){
        String s1 = this.navn;
        String s2 = annen.navn; //annen.hentNavn()

        int sammenlign = s1.compareTo(s2);

        if (sammenlign < 0) {
            return -1;
        } else if (sammenlign > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void leggTilResept(Resept r) {
        resepter.leggTil(r);
    }

    public Liste hentListeResepter(){
        return resepter;
    }

    @Override
    public String toString() {
        return navn + ", Avtalenummer: " + avtalenummer;
    }
}
