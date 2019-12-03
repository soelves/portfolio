import java.util.Random;
import java.lang.System;
import java.util.Arrays;
public class Hovedprogram{
    public static void main(String[] args) {
        String s = "";
        TilTekst t = new TilTekst();
        s = s + "-------------------------- \n";

        if(args.length == 0){
            for(int i = 0; i < 4; i++){
                int[] liste1 = {0,1,2,3,4,5,6,7,8,9};
                int[] liste2 = {9,8,7,6,5,4,3,2,1,0};
                int[] liste3 = {7,5,8,8,1,1,3,6,9,2};


                if(i == 5){
                    /*s = s + "SelectionSort:\n";
                    s = s + "Pre:  " + t.tekst(liste1) + "\n";
                    SelectionSort selection1 = new SelectionSort(liste1);
                    s = s + "Post: " + t.tekst(liste1) + "\n";
                    s = s + "-------------------------- \n";
                    s = s + "Pre:  " + t.tekst(liste2) + "\n";
                    SelectionSort selection2 = new SelectionSort(liste2);
                    s = s + "Post: " + t.tekst(liste2) + "\n";
                    s = s + "-------------------------- \n";
                    s = s + "Pre:  " + t.tekst(liste3) + "\n";
                    SelectionSort selection3 = new SelectionSort(liste3);
                    s = s + "Post: " + t.tekst(liste3) + "\n";
                    s = s + "-------------------------- \n";
                    */

                    System.out.println("SelectionSort:\n");
                    System.out.println("Pre:  " + t.tekst(liste1) + "\n");
                    SelectionSort selection1 = new SelectionSort(liste1);
                    System.out.println("Post: " + t.tekst(liste1) + "\n");
                    System.out.println("-------------------------- \n");
                    System.out.println("Pre:  " + t.tekst(liste2) + "\n");
                    SelectionSort selection2 = new SelectionSort(liste2);
                    System.out.println("Post: " + t.tekst(liste2) + "\n");
                    System.out.println("-------------------------- \n");
                    System.out.println("Pre:  " + t.tekst(liste3) + "\n");
                    SelectionSort selection3 = new SelectionSort(liste3);
                    System.out.println("Post: " + t.tekst(liste3) + "\n");

                }
                if(i == 5){
                    /*s = s + "InsertionSort:\n";
                    s = s + "Pre:  " + t.tekst(liste1) + "\n";
                    InsertionSort insertion1 = new InsertionSort(liste1);
                    s = s + "Post: " + t.tekst(liste1) + "\n";
                    s = s + "-------------------------- \n";
                    s = s + "Pre:  " + t.tekst(liste2) + "\n";
                    InsertionSort insertion2 = new InsertionSort(liste2);
                    s = s + "Post: " + t.tekst(liste2) + "\n";
                    s = s + "-------------------------- \n";
                    s = s + "Pre:  " + t.tekst(liste3) + "\n";
                    InsertionSort insertion3 = new InsertionSort(liste3);
                    s = s + "Post: " + t.tekst(liste3) + "\n";
                    s = s + "-------------------------- \n";
                    */

                    System.out.println("InsertionSort:\n");
                    System.out.println("Pre:  " + t.tekst(liste1) + "\n");
                    InsertionSort nummer1 = new InsertionSort(liste1);
                    System.out.println("Post: " + t.tekst(liste1) + "\n");
                    System.out.println("-------------------------- \n");
                    System.out.println("Pre:  " + t.tekst(liste2) + "\n");
                    InsertionSort nummer2 = new InsertionSort(liste2);
                    System.out.println("Post: " + t.tekst(liste2) + "\n");
                    System.out.println("-------------------------- \n");
                    System.out.println("Pre:  " + t.tekst(liste3) + "\n");
                    InsertionSort nummer3 = new InsertionSort(liste3);
                    System.out.println("Post: " + t.tekst(liste3) + "\n");

                }
                if(i == 5){
                    /*s = s + "QuickSort:\n";
                    s = s + "Pre:  " + t.tekst(liste1) + "\n";
                    QuickSort quick1 = new QuickSort(liste1);
                    s = s + "Post: " + t.tekst(liste1) + "\n";
                    s = s + "-------------------------- \n";
                    s = s + "Pre:  " + t.tekst(liste2) + "\n";
                    QuickSort quick2 = new QuickSort(liste2);
                    s = s + "Post: " + t.tekst(liste2) + "\n";
                    s = s + "-------------------------- \n";
                    s = s + "Pre:  " + t.tekst(liste3) + "\n";
                    QuickSort quick3 = new QuickSort(liste3);
                    s = s + "Post: " + t.tekst(liste3) + "\n";
                    s = s + "-------------------------- \n";
                    */

                    System.out.println("QuickSort:\n");
                    System.out.println("Pre:  " + t.tekst(liste1) + "\n");
                    QuickSort nummer1 = new QuickSort(liste1);
                    System.out.println("Post: " + t.tekst(liste1) + "\n");
                    System.out.println("-------------------------- \n");
                    System.out.println("Pre:  " + t.tekst(liste2) + "\n");
                    QuickSort nummer2 = new QuickSort(liste2);
                    System.out.println("Post: " + t.tekst(liste2) + "\n");
                    System.out.println("-------------------------- \n");
                    System.out.println("Pre:  " + t.tekst(liste3) + "\n");
                    QuickSort nummer3 = new QuickSort(liste3);
                    System.out.println("Post: " + t.tekst(liste3) + "\n");
                }
                if(i == 0){
                    /*s = s + "BucketSort:\n";
                    s = s + "Pre:  " + t.tekst(liste1) + "\n";
                    BucketSort bucket1 = new BucketSort(liste1);
                    s = s + "Post: " + t.tekst(liste1) + "\n";
                    s = s + "-------------------------- \n";
                    s = s + "Pre:  " + t.tekst(liste2) + "\n";
                    BucketSort bucket2 = new BucketSort(liste2);
                    s = s + "Post: " + t.tekst(liste2) + "\n";
                    s = s + "-------------------------- \n";
                    s = s + "Pre:  " + t.tekst(liste3) + "\n";
                    BucketSort bucket3 = new BucketSort(liste3);
                    s = s + "Post: " + t.tekst(liste3) + "\n";
                    s = s + "-------------------------- \n";
                    */

                    System.out.println("BucketSort:\n");
                    System.out.println("Pre:  " + t.tekst(liste1) + "\n");
                    BucketSort nummer1 = new BucketSort(liste1);
                    System.out.println("Post: " + t.tekst(liste1) + "\n");
                    System.out.println("-------------------------- \n");
                    System.out.println("Pre:  " + t.tekst(liste2) + "\n");
                    BucketSort nummer2 = new BucketSort(liste2);
                    System.out.println("Post: " + t.tekst(liste2) + "\n");
                    System.out.println("-------------------------- \n");
                    System.out.println("Pre:  " + t.tekst(liste3) + "\n");
                    BucketSort nummer3 = new BucketSort(liste3);
                    System.out.println("Post: " + t.tekst(liste3) + "\n");
                }
            }
            System.out.println(s);
        }else{
            int input = Integer.parseInt(args[0]);

            for(int j = 0; j < 5; j++){

                /*
                int[] stigende = new int[input];
                for(int i = 0; i < input; i++){
                    stigende[i] = i;
                }
                int[] synkende = new int[input];
                for(int i = 1; i < (input); i++){
                    synkende[i] = input - i;
                }
                int[] tilfeldig = new int[input];
                Random r = new Random();
                for(int i = 0; i < input; i++){
                    tilfeldig[i] = r.nextInt(input);
                }
                */
                int[] stigende = new int[input];
                for(int i = 0; i < input; i++){
                    if(i < ((input/10)*1)){
                        stigende[i] = 0;
                    }
                    if(i < ((input/10)*2) && i > ((input/10)*1)){
                        stigende[i] = 1;
                    }
                    if(i < ((input/10)*3) && i > ((input/10)*2)){
                        stigende[i] = 2;
                    }
                    if(i < ((input/10)*4) && i > ((input/10)*3)){
                        stigende[i] = 3;
                    }
                    if(i < ((input/10)*5) && i > ((input/10)*4)){
                        stigende[i] = 4;
                    }
                    if(i < ((input/10)*6) && i > ((input/10)*5)){
                        stigende[i] = 5;
                    }
                    if(i < ((input/10)*7) && i > ((input/10)*6)){
                        stigende[i] = 6;
                    }
                    if(i < ((input/10)*8) && i > ((input/10)*7)){
                        stigende[i] = 7;
                    }
                    if(i < ((input/10)*9) && i > ((input/10)*8)){
                        stigende[i] = 8;
                    }
                    if(i < input && i > ((input/10)*9)){
                        stigende[i] = 9;
                    }
                }
                int[] synkende = new int[input];
                for(int i = 1; i < (input); i++){
                    if(i < ((input/10)*1)){
                        stigende[i] = 9;
                    }
                    if(i < ((input/10)*2) && i > ((input/10)*1)){
                        stigende[i] = 8;
                    }
                    if(i < ((input/10)*3) && i > ((input/10)*2)){
                        stigende[i] = 7;
                    }
                    if(i < ((input/10)*4) && i > ((input/10)*3)){
                        stigende[i] = 6;
                    }
                    if(i < ((input/10)*5) && i > ((input/10)*4)){
                        stigende[i] = 5;
                    }
                    if(i < ((input/10)*6) && i > ((input/10)*5)){
                        stigende[i] = 4;
                    }
                    if(i < ((input/10)*7) && i > ((input/10)*6)){
                        stigende[i] = 3;
                    }
                    if(i < ((input/10)*8) && i > ((input/10)*7)){
                        stigende[i] = 2;
                    }
                    if(i < ((input/10)*9) && i > ((input/10)*8)){
                        stigende[i] = 1;
                    }
                    if(i < input && i > ((input/10)*9)){
                        stigende[i] = 0;
                    }
                }
                int[] tilfeldig = new int[input];
                Random r = new Random();
                for(int i = 0; i < input; i++){
                    tilfeldig[i] = r.nextInt(9);
                }


                if(j == 9){
                    long t1 = System.nanoTime();
                    SelectionSort s1 = new SelectionSort(stigende);
                    double tid1 = (System.nanoTime() - t1)/10000000.0;

                    long t2 = System.nanoTime();
                    SelectionSort s2 = new SelectionSort(synkende);
                    double tid2 = (System.nanoTime() - t2)/10000000.0;

                    long t3 = System.nanoTime();
                    SelectionSort s3 = new SelectionSort(tilfeldig);
                    double tid3 = (System.nanoTime() - t3)/10000000.0;

                    System.out.println(args[0] + " SelectionSort:\nStigende "
                    + Double.toString(tid1) + "\nSynkende: " + Double.toString(tid2) +
                    "\nTilfeldig: " + Double.toString(tid3) + "\n");
                }
                if(j == 9){
                    long t1 = System.nanoTime();
                    InsertionSort i1 = new InsertionSort(stigende);
                    double tid1 = (System.nanoTime() - t1)/10000000.0;

                    long t2 = System.nanoTime();
                    InsertionSort i2 = new InsertionSort(synkende);
                    double tid2 = (System.nanoTime() - t2)/10000000.0;

                    long t3 = System.nanoTime();
                    InsertionSort i3 = new InsertionSort(tilfeldig);
                    double tid3 = (System.nanoTime() - t3)/10000000.0;

                    System.out.println(args[0] + " InsertionSort:\nStigende "
                    + Double.toString(tid1) + "\nSynkende: " + Double.toString(tid2) +
                    "\nTilfeldig: " + Double.toString(tid3) + "\n");
                }
                if(j == 9){
                    long t1 = System.nanoTime();
                    QuickSort q1 = new QuickSort(stigende);
                    double tid1 = (System.nanoTime() - t1)/10000000.0;

                    long t2 = System.nanoTime();
                    QuickSort q2 = new QuickSort(synkende);
                    double tid2 = (System.nanoTime() - t2)/10000000.0;

                    long t3 = System.nanoTime();
                    QuickSort q3 = new QuickSort(tilfeldig);
                    double tid3 = (System.nanoTime() - t3)/10000000.0;

                    System.out.println(args[0] + " QuickSort:\nStigende "
                    + Double.toString(tid1) + "\nSynkende: " + Double.toString(tid2) +
                    "\nTilfeldig: " + Double.toString(tid3) + "\n");
                }
                if(j == 3){
                    long t1 = System.nanoTime();
                    BucketSort b1 = new BucketSort(stigende);
                    double tid1 = (System.nanoTime() - t1)/10000000.0;

                    long t2 = System.nanoTime();
                    BucketSort b2 = new BucketSort(synkende);
                    double tid2 = (System.nanoTime() - t2)/10000000.0;

                    long t3 = System.nanoTime();
                    BucketSort b3 = new BucketSort(tilfeldig);
                    double tid3 = (System.nanoTime() - t3)/10000000.0;

                    System.out.println(args[0] + " BucketSort:\nStigende "
                    + Double.toString(tid1) + "\nSynkende: " + Double.toString(tid2) +
                    "\nTilfeldig: " + Double.toString(tid3) + "\n");
                }
                if(j == 4){
                    long t1 = System.nanoTime();
                    Arrays.sort(stigende);
                    double tid1 = (System.nanoTime() - t1)/10000000.0;

                    long t2 = System.nanoTime();
                    Arrays.sort(synkende);
                    double tid2 = (System.nanoTime() - t2)/10000000.0;

                    long t3 = System.nanoTime();
                    Arrays.sort(tilfeldig);
                    double tid3 = (System.nanoTime() - t3)/10000000.0;

                    System.out.println(args[0] + " Arrays.sort():\nStigende "
                    + Double.toString(tid1) + "\nSynkende: " + Double.toString(tid2) +
                    "\nTilfeldig: " + Double.toString(tid3) + "\n");
                }

            }
        }


        /*for(int j = 0; j < 4; j++){
            int[] stigende = new int[1000000000];
            for(int i = 0; i < 1000000000; i++){
                stigende[i] = i;
            }
            int[] synkende = new int[1000000000];
            for(int i = 0; i < 999999999; i++){
                synkende[i] = 1000000000 - i;
            }
            int[] tilfeldig = new int[1000000000];
            Random r = new Random();
            for(int i = 0; i < 1000000000; i++){
                tilfeldig[i] = r.nextInt(100000000);
            }

            if(j == 0){
                long t1 = System.nanoTime();
                SelectionSort s1 = new SelectionSort(stigende);
                double tid1 = (System.nanoTime() - t1)/10000000.0;

                long t2 = System.nanoTime();
                SelectionSort s2 = new SelectionSort(synkende);
                double tid2 = (System.nanoTime() - t2)/10000000.0;

                long t3 = System.nanoTime();
                SelectionSort s3 = new SelectionSort(tilfeldig);
                double tid3 = (System.nanoTime() - t3)/10000000.0;

                System.out.println("100000 SelectionSort:\nStigende "
                + Double.toString(tid1) + "\nSynkende: " + Double.toString(tid2) +
                "\nTilfeldig: " + Double.toString(tid3) + "\n");
            }
            if(j == 1){
                long t1 = System.nanoTime();
                InsertionSort i1 = new InsertionSort(stigende);
                double tid1 = (System.nanoTime() - t1)/10000000.0;

                long t2 = System.nanoTime();
                InsertionSort i2 = new InsertionSort(synkende);
                double tid2 = (System.nanoTime() - t2)/10000000.0;

                long t3 = System.nanoTime();
                InsertionSort i3 = new InsertionSort(tilfeldig);
                double tid3 = (System.nanoTime() - t3)/10000000.0;

                System.out.println("100000 InsertionSort:\nStigende "
                + Double.toString(tid1) + "\nSynkende: " + Double.toString(tid2) +
                "\nTilfeldig: " + Double.toString(tid3) + "\n");
            }
            if(j == 2){
                long t1 = System.nanoTime();
                QuickSort q1 = new QuickSort(stigende);
                double tid1 = (System.nanoTime() - t1)/10000000.0;

                long t2 = System.nanoTime();
                QuickSort q2 = new QuickSort(synkende);
                double tid2 = (System.nanoTime() - t2)/10000000.0;

                long t3 = System.nanoTime();
                QuickSort q3 = new QuickSort(tilfeldig);
                double tid3 = (System.nanoTime() - t3)/10000000.0;

                System.out.println("100000 QuickSort:\nStigende "
                + Double.toString(tid1) + "\nSynkende: " + Double.toString(tid2) +
                "\nTilfeldig: " + Double.toString(tid3) + "\n");
            }
            if(j == 3){
                long t1 = System.nanoTime();
                BucketSort b1 = new BucketSort(stigende);
                double tid1 = (System.nanoTime() - t1)/10000000.0;

                long t2 = System.nanoTime();
                BucketSort b2 = new BucketSort(synkende);
                double tid2 = (System.nanoTime() - t2)/10000000.0;

                long t3 = System.nanoTime();
                BucketSort b3 = new BucketSort(tilfeldig);
                double tid3 = (System.nanoTime() - t3)/10000000.0;

                System.out.println("100000 BucketSort:\nStigende "
                + Double.toString(tid1) + "\nSynkende: " + Double.toString(tid2) +
                "\nTilfeldig: " + Double.toString(tid3) + "\n");
            }
        }*/
    }
}
