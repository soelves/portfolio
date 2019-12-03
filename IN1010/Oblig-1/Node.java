public class Node {
  int minne;              //Lagringsplass i GB
  int prosessorer;        //Antall prosessorer

  public Node(int nyttMinne, int nyeProsessorer) {
    minne = nyttMinne;
    prosessorer = nyeProsessorer;
  }

  public boolean nokMinne(int krevd) {  //For å kunne sjekke om en node har nok minne.
     if (minne >= krevd) {
       return true;
     }else{
       return false;
     }
  }
  public int hentProsessorer() {  //For å vite hvor mange prosessorer en node har.
    return prosessorer;
  }
}
