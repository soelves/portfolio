import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Regneklynge{
  ArrayList<Rack> klynge = new ArrayList<Rack>();  //Igjen, valgte ArrayList siden lengden ikke er fast.
  public int noderPerRack;
  public String fil;

  public void settInn(Node node) { //For å sette inn en Node. Sjekker om det finnes et rack å sette noden i,
    boolean lagtTil = false;       //hvis ikke opretter den en ny, ellers går den gjennom og sjekker om det er plass,
                                   //hvis ikke, så oprettes en ny.
    if (klynge.size() < 1) {
      Rack rack = new Rack();
      rack.settInn(node);
      klynge.add(rack);
    }else {
      for (Rack rack : klynge) {
        if (rack.antallNoder() < noderPerRack) {
          rack.settInn(node);
          lagtTil = true;

        }
      }
      if (lagtTil == false) {
        Rack rack = new Rack();
        rack.settInn(node);
        klynge.add(rack);
      }
    }
  }

  public Regneklynge(String fil) {  //Konstuktør som leser inn data fra fil, for så å delegere dataen der den skal.
    this.fil = fil;
    File inputFil = new File(fil);
    Scanner in = null;

    try {
      in = new Scanner(inputFil);
    } catch (FileNotFoundException e) {
      System.out.println("Fant ikke filen.");
    }

    int indeks = 0;
    String linje = "";
    while (in.hasNextLine()) {
      linje = in.nextLine();

      if (indeks == 0) {
        noderPerRack = Integer.parseInt(linje);
      } else {
        String[] delt = linje.split(" ");

        int antallNoder = Integer.parseInt(delt[0]);
        int minne = Integer.parseInt(delt[1]);
        int prosessorer = Integer.parseInt(delt[2]);
        Node node = new Node(minne,prosessorer);
        for (int teller = 0; teller < antallNoder; teller++) {
          settInn(node);

      }
    }
    indeks += 1;
  }
}



    public int antProsKlynge() {  //For å vite hvor mange prosessorer det finnes i en klynge.
      int antall = 0;

      for (Rack etRack : klynge) {
        antall += etRack.antProsRack();
      }

      return antall;
    }

  public int nokMinneKlynge(int krevd) {  //For å vite hvor mange noder som har et minimums antall GB i en klynge.
    int antall = 0;

    for (Rack etRack : klynge) {
      antall += etRack.nokMinneRack(krevd);
    }
    return antall;
  }
  public int antRacks() {  //For å vite hvor mange racks det er i en klynge.
    return klynge.size();
  }
}
