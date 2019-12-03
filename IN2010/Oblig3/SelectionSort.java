public class SelectionSort{
    int[] sortertListe;
    TilTekst tilTekst = new TilTekst();

    public SelectionSort(int[] liste){
        int minst;

        for(int i = 0; i < liste.length; i++){
            minst = i;
            for(int j = (i + 1); j < liste.length; j++){
                if(liste[j] < liste[minst]){
                    minst = j;
                    System.out.println(tilTekst.tekst(liste));
                }
            }
            if(i != minst){
                int t;
                t = liste[minst];
                liste[minst] = liste[i];
                liste[i] = t;
                System.out.println(tilTekst.tekst(liste));
            }
        }
        sortertListe = liste;
    }
}
