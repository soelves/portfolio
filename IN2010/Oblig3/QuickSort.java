import java.util.concurrent.ThreadLocalRandom;
public class QuickSort{
    int[] sortertListe;
    TilTekst tilTekst = new TilTekst();

    public QuickSort(int[] liste){
        int a = 0;
        int b = liste.length - 1;

        inPlaceQuickSort(liste, a, b);

        sortertListe = liste;
    }

    public int inPlacePartition(int[] liste, int a, int b){
        int r = ThreadLocalRandom.current().nextInt(a, b);

        int t1 = liste[r];
        liste[r] = liste[b];
        liste[b] = t1;
        System.out.println(tilTekst.tekst(liste));

        int p = liste[b];
        int l = a;
        r = b - 1;

        while(l <= r){
            while(l <= r && liste[l] <= p){
                l = l + 1;
            }
            while(l <= r && liste[r] >= p){
                r = r - 1;
            }
            if(l < r){
                int t2 = liste[r];
                liste[r] = liste[l];
                liste[l] = t2;
                System.out.println(tilTekst.tekst(liste));
            }
        }
        int t3 = liste[l];
        liste[l] = liste[b];
        liste[b] = t3;
        System.out.println(tilTekst.tekst(liste));

        return l;
    }

    public void inPlaceQuickSort(int[] liste, int a, int b){
        while(a < b){
            int l = inPlacePartition(liste, a, b);
            if((l - a) < (b - l)){
                inPlaceQuickSort(liste, a, (l - 1));
                a = l + 1;
            }else{
                inPlaceQuickSort(liste, (l + 1), b);
                b = l - 1;
            }
        }
    }
}
