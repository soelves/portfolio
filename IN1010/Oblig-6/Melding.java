import java.util.*;
class Melding implements Comparable<Melding>{
    String melding;
    static int sn = 0;
    int sekvensNummer;
    int kanalID;

    public Melding(String m, Kanal k){
        melding = m;
        sekvensNummer = sn;
        sn++;
        kanalID = k.hentId();
    }

    public String giMelding(){
        return melding;
    }

    public void endreMelding(String m){
        melding = m;
    }

    public int hentKanalID(){
        return kanalID;
    }

    @Override
    public int compareTo(Melding sMelding){
        return this.sekvensNummer - sMelding.sekvensNummer;
        //return Integer.compare(this.sekvensNummer, sMelding.sekvensNummer);
    }
}
