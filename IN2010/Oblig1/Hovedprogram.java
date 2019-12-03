public class Hovedprogram{
    public static void main(String[] args) {
        Integer[] liste = {10, 8, 12, 3, 5, 18, 15, 7};

        BSTree tre = new BSTree(liste);

        int[] sortertListe = tre.sortedArray();
        int[] sortertRekkevidde = tre.findInRange(6,16);


        for(int i = 0; i < sortertListe.length; i++){
            System.out.println(sortertListe[i]);
        }

        System.out.println("\n");

        for(int i = 0; i < sortertRekkevidde.length; i++){
            System.out.println(sortertRekkevidde[i]);
        }


        System.out.println(tre.findNearestSmallerThan(8));
    }
}
