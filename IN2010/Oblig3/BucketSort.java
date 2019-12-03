public class BucketSort{
    int[] sortertListe;

    public BucketSort(int[] liste){
        int[] b = new int[liste.length];

        for(int i = 0; i < liste.length; i++){
            int k = liste[i];
            b[k]++;
        }
        int lengde = 0;
        for(int i = 0; i < b.length; i++){
            for(int j = 0; j < b[i]; j++){
                liste[lengde] = i;
                lengde++;
            }
        }
        sortertListe = liste;
    }
}
