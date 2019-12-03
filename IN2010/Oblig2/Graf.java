import java.util.*;
import java.io.*;

public class Graf{

    //Jobb/task/node klassen, inneholder ulike data og referanser til andre
    //jobber.
    public class Jobb{
        int id, tid, ansatte;
        String navn;
        Integer tidligsteStart, tidligsteFerdig, senesteStart, slack;
        List<Jobb> utKanter;
        List<Integer> tall;
        List<Jobb> forfedre;
        int tellForfedre;
        String tilstand;
        boolean kritisk = false;
        int loopTeller;

        //Konstruktør, oppretter listene som hver jobb trenger.
        public Jobb(){
            tall = new ArrayList<>();
            forfedre = new ArrayList<>();
            utKanter = new ArrayList<>();
        }

        //toString metode som hjelper til med utskriften for hver enkelt jobb.
        @Override
        public String toString(){
            String s = "";

            s = s + "ID: " + Integer.toString(id) + ", ";
            s = s + navn + ", ";
            s = s + "Tid: " + Integer.toString(tid) + ", ";
            s = s + "Ansatte: " + Integer.toString(ansatte) + ", ";
            s = s + "tStart: " + Integer.toString(tidligsteStart) + ", ";
            s = s + "tFerdig: " + Integer.toString(tidligsteFerdig) + ", ";
            s = s + "sStart: " + Integer.toString(senesteStart) + ", ";
            s = s + "Slack: " + Integer.toString(slack);
            if(kritisk == true){
                s = s + ", Jobben er kritisk!";
            }
            s = s + "\n";

            return s;
        }
    }

    private Scanner s;

    List<Jobb> alleJobber = new ArrayList<Jobb>();
    String loop;

    //Konstruktør, tar inn en fil, lager jobber og gir de dataen sin.
    //Alle jobbene blir lagt i en liste.
    public Graf(String filnavn){
        try{
            File fil = new File(filnavn);
            s = new Scanner(fil);
            }
            catch(Exception e){
                System.out.println("Fant ikke filen.");
        }

        int antall = s.nextInt();

        for(int i = 0; i < antall; i++){
            Jobb j = new Jobb();

            j.id = s.nextInt();
            j.navn = s.next();
            j.tid = s.nextInt();
            j.ansatte = s.nextInt();

            boolean stopp = false;
            int enJobb = 0;

            while(stopp == false){
                enJobb = s.nextInt();

                if(enJobb == 0){
                    stopp = true;
                }else{
                    j.tall.add(enJobb);
                    j.tellForfedre++;
                }
            }
            alleJobber.add(j);
        }
        for(int i = 0; i < antall; i++){
            Jobb j = alleJobber.get(i);
            if(j.tellForfedre > 0){
                for(int k = 0; k < j.tellForfedre; k++){
                    int ID = j.tall.get(k);
                    Jobb forelder = hentJobb(ID);
                    forelder.utKanter.add(j);
                    j.forfedre.add(forelder);
                }
            }
        }
    }

    //Sjekker om det finnes en loop i grafen. Først setter jeg alleJobber
    //til usett, så bruker jeg hjelpemetoden let til å finne en evt. loop.
    public String loopSjekk(Graf f){
        loop = "Ingen loop.";
        boolean harLoop = false;

        for(int i = 0; i < f.alleJobber.size(); i++){

            Jobb enJobb = f.alleJobber.get(i);
            enJobb.tilstand = "usett";
        }

        for(int i = 0; i < f.alleJobber.size(); i++){
            let(f.alleJobber.get(i));

            Jobb enJobb = f.alleJobber.get(i);
        }

        return loop;
    }

    //Tatt fra forelesning. Setter en node igang, og viderefører dette til
    //en nodes utKanter, til noden som blir truffet ikke har noen utkanter,
    //eller kommer tilbake til seg selv.

    static int statiskLoopTeller = 0;

    public void let(Jobb j){
        if(j.tilstand == "igang"){
            loop = "Fant loop fra/til ID: " + Integer.toString(j.id) + "\n";
            String startOgStopp = Integer.toString(j.id);

            List<Integer> loopListe = new ArrayList<Integer>();

            for(int i = 0; i < alleJobber.size(); i++){

                if(alleJobber.get(i).tilstand == "igang"){
                    loopListe.add(alleJobber.get(i).loopTeller);
                }
            }

            Collections.sort(loopListe);

            List<Integer> loopID = new ArrayList<Integer>();

            for(int i = 0; i < loopListe.size(); i++){
                for(int k = 0; k < alleJobber.size(); k++){
                    if(loopListe.get(i) == alleJobber.get(k).loopTeller){
                        loopID.add(alleJobber.get(k).id);
                    }
                }
            }

            for(int i = 0; i < loopID.size(); i++){
                loop = loop + Integer.toString(loopID.get(i)) + " - ";
            }

            loop = loop + startOgStopp + ".";

        }else if(j.tilstand == "usett"){
            j.tilstand = "igang";
            j.loopTeller = statiskLoopTeller;
            statiskLoopTeller++;
            for(int i = 0; i < j.utKanter.size(); i++){
                let(j.utKanter.get(i));
            }
            j.tilstand = "ferdig";
        }
    }

    //Henter en jobb ut ifra jobbens id.
    public Jobb hentJobb(int id){
        for(int i = 0; i < alleJobber.size(); i++){
            if(alleJobber.get(i).id == id){
                return alleJobber.get(i);
            }
        }
        return null;
    }

    static int samlaTid;
    static int sluttTid;
    static String skjema;

    //Legger de siste jobbene i en egen liste. Deretter settes tidligsteStart
    //og tidligsteFerdig i timelapse1, og senesteStart i timelapse2. Til slutt
    //finner jeg raskeste gjennomgang.
    public String tidsSkjema(Graf f){
        List<Jobb> sluttJobber = new ArrayList<Jobb>();

        for(int i = 0; i < f.alleJobber.size(); i++){
            if(f.alleJobber.get(i).utKanter.size() == 0){
                sluttJobber.add(f.alleJobber.get(i));
            }
        }
        for(int i = 0; i < sluttJobber.size(); i++){
            timelapse1(samlaTid, sluttJobber.get(i));
        }

        for(int i = 0; i < sluttJobber.size(); i++){
            timelapse2(sluttJobber.get(i));
        }

        return "Raskeste gjennomgang er " + Integer.toString(sluttTid);

    }

    //Hjelpemetode til tidsSkjema.
    public void timelapse1(int samlaTid, Jobb j){
        if(j.forfedre.size() != 0){
            for(int i = 0; i < j.forfedre.size(); i++){
                timelapse1(samlaTid, j.forfedre.get(i));
            }
        }
        if(j.forfedre.size() == 0){
            j.tidligsteStart = 0;
            j.tidligsteFerdig = j.tid;

        }else{
            int storst = 0;
            for(int i = 0; i < j.forfedre.size(); i++){
                if(j.forfedre.get(i).tid > storst){
                    storst = j.forfedre.get(i).tid;
                    j.tidligsteStart = j.forfedre.get(i).tidligsteFerdig;
                }
            }
            j.tidligsteFerdig = j.tidligsteStart + j.tid;
            if(samlaTid < j.tidligsteFerdig){
                samlaTid = j.tidligsteFerdig;
            }
        }

        settTid(samlaTid);

    }

    //Hjelpemetode til tidsSkjema.
    public void timelapse2(Jobb j){
        if(j.forfedre.size() != 0){
            for(int i = 0; i < j.forfedre.size(); i++){
                timelapse2(j.forfedre.get(i));
            }
        }
        if(j.utKanter.size() != 0){
            Integer minst = null;
            for(int i = 0; i < j.utKanter.size(); i++){
                if(minst == null){
                    minst = j.utKanter.get(i).tidligsteStart;
                }else{
                    if(minst > j.utKanter.get(i).tidligsteStart){
                        minst = j.utKanter.get(i).tidligsteStart;
                    }
                }
            }
            if(minst > j.tidligsteFerdig){
                j.slack = minst - j.tidligsteFerdig;
                j.senesteStart = j.tidligsteStart + j.slack;
            }else{
                j.slack = 0;
                j.kritisk = true;
                j.senesteStart = j.tidligsteStart;
            }
        }else{
            if(sluttTid > j.tidligsteFerdig){
                j.slack = sluttTid - j.tidligsteFerdig;
                j.senesteStart = j.tidligsteStart + j.slack;
            }else{
                j.slack = 0;
                j.kritisk = true;
                j.senesteStart = j.tidligsteStart;
            }
        }
    }

    //Hjelpemetode til timelapse2.
    public void settTid(int t){
        sluttTid = t;
    }

    //Resultat lager en string til output av hvordan jobbene
    //utføres underveis i grafen.
    public String resultat(Graf f){
        String s = "";
        int teller = 0;
        int paaJobb = 0;

        for(int k = 0; k < (sluttTid + 1); k++){
        boolean oppdatering = false;
            for(int i = 0; i < (alleJobber.size()); i++){

                if(alleJobber.get(i).tidligsteFerdig == teller){
                    s = s + "Ferdig: "
                    + Integer.toString(alleJobber.get(i).id) + "\n";
                    paaJobb = paaJobb - alleJobber.get(i).ansatte;
                    oppdatering = true;
                }

                if(alleJobber.get(i).tidligsteStart == teller){
                    s = s + "Starter: "
                    + Integer.toString(alleJobber.get(i).id) + "\n";
                    paaJobb = paaJobb + alleJobber.get(i).ansatte;
                    oppdatering = true;
                }
            }

            if(oppdatering == true){
                s = s + "Ansatte som jobber: "
                + Integer.toString(paaJobb) + "\n";
                s = s + "Tid: " + teller + "\n";
                s = s + "----------\n";
            }
            teller ++;
        }
        s = s + "# Raskeste mulige  gjennomgang av prosjektet er "
        + Integer.toString(sluttTid) + " # \n";
        s = s + f.toString();

        return s;
    }

    //Representasjon av en graf.
    @Override
    public String toString(){
        String s = "";

        int str = alleJobber.size();

        for(int i = 0; i < str; i++){
            s = s + alleJobber.get(i);
        }

        return s;

    }
}
