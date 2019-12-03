class Lege{
    public String navn;
    public int avtalenummer;

    public Lege(String n, int a){
        navn = n;
        avtalenummer = a;
    }

    public String hentNavn(){
        return navn;
    }
}
