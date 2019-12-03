public class Hovedprogram {
  public static void main(String[] args) {
    Regneklynge Abel = new Regneklynge("regneklynge.txt");
    /*Node Node64 = new Node(,);
    Node Node1024 = new Node(,);

    for (int teller = 0; teller < 650; teller++) {
      Abel.settInn(Node64);
    }
    for (int teller = 0; teller < 16; teller++) {
      Abel.settInn(Node1024);
    }*/

    System.out.println("Noder med minst 32 GB: " + Abel.nokMinneKlynge(32));
    System.out.println("Noder med minst 64 GB: " + Abel.nokMinneKlynge(64));
    System.out.println("Noder med minst 128 GB: " + Abel.nokMinneKlynge(128));
    System.out.println("");
    System.out.println("Antall prosessorer: " + Abel.antProsKlynge());
    System.out.println("Antall rack: " + Abel.antRacks());
  }
}
