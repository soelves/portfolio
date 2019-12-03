class Pasient {
    static int pID = 0;
    Stabel<Resept> resepter;
    String navn;
    String fNummer;
    int pasientID;

    public Pasient(String n, String f){
        navn = n;
        fNummer = f;
        pasientID = pID;
        pID++;
        resepter = new Stabel<Resept>();
    }

    public void leggTil(Resept r){
        resepter.leggPaa(r);
    }

    public Stabel hentListe(){
        return resepter;
    }

    @Override
    public String toString() {
        return pasientID + " : " + navn + ", Fødselsnummer: " + fNummer;
    }
}
