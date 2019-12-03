import java.util.ArrayList;

public class Rack{
  ArrayList<Node> rack = new ArrayList<Node>();  //Jeg valgte ArrayList, siden lengden ikke er fast.

  public int antProsRack() {  //For å vite hvor mange prosessorer det er i ett rack.
    int antall = 0;

    for (Node node : rack) {
      antall += node.hentProsessorer();
    }

    return antall;
  }
  public int nokMinneRack(int krevd) {  //For å vite hvor mange noder det er i et rack med et minimums antall GB.
    int antall = 0;
    for (Node node : rack) {
      if (node.nokMinne(krevd) == true) {
        antall++;
      }
    }
    return antall;

  }
  public void settInn(Node node) {  //For å kunne sette inn en node i et rack.
    rack.add(node);
  }
  public int antallNoder() {  //For å vite hvor mange noder det er i ett rack.
    int antall;
    antall = rack.size();
    return antall;
  }
}
