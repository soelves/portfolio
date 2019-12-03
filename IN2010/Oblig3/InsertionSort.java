public class InsertionSort{
    int[] sortertListe;
    TilTekst tilTekst = new TilTekst();

    public InsertionSort(int[] liste){

        for(int i = 0; i < (liste.length); i++){
            int x = liste[i];
            int j = i;
            while(j > 0 && x < liste[j-1]){
                liste[j] = liste[j - 1];
                j = j-1;
                System.out.println(tilTekst.tekst(liste));

            }
            liste[j] = x;
            //System.out.println(tilTekst.tekst(liste));
        }
        sortertListe = liste;
    }
}
