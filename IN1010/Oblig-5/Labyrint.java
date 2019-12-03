import java.util.Scanner;
import java.io.*;

class Labyrint {
    Rute[][] rutenett;
    Labyrint l;
    int rad;
    int kolonne;
    Liste<String> veier = new Lenkeliste();

    private Labyrint(int r, int k, Rute[][] ruter){
        rad = r;
        kolonne = k;

        rutenett = ruter;
    }

    //Her bruker jeg en toString metode i Hvit og Sort Rute til å printe ut
    //Labyrinten. Etter hver rad har blitt fylt ut, så legger jeg på et linjeskift.
    //Alle tegnene blir samlet i en String, som blir returnert til slutt.
    @Override
    public String toString(){
        String s = "Labyrint:\n";

        for(int i = 0; i < rad; i++){
            for(int j = 0; j < kolonne; j++){
                s = s + rutenett[i][j].toString();
            }
            s = s + "\n";

        }
        return s;
    }

    public static Labyrint lesFraFil(File fil) throws FileNotFoundException{
        Rute[][] ruter;
        int r;
        int k;

        Scanner scanner = new Scanner(fil);

        r = Integer.parseInt(scanner.next());
        k = Integer.parseInt(scanner.next());

        ruter = new Rute[r][k];

//Her henter jeg først ut en rad med tegn, som jeg så gjør om til en Array
//av char. Så sjekker jeg et og et tegn, og legger inn riktig rute i en
//2D Array ut ifra den sjekken.
        while(scanner.hasNext()){
            for(int i = 0; i < r; i++){
                String symbol = scanner.next();
                char[] tegn = symbol.toCharArray();

                for(int j = 0; j < k; j++){
                    if(tegn[j] == '#'){
                        ruter[i][j] = new SortRute(i, j);
                    } else {
                        if(i == 0 | i == (r-1) | j == 0 | j == (k-1)){
                            ruter[i][j] = new Aapning(i, j);
                        } else {
                            ruter[i][j] = new HvitRute(i, j);
                        }
                    }
                }
            }
        }

//Her bruker jeg en metode jeg lagde i ruter, for å sette hver ruters nabo.
        for(int i = 0; i < r; i++){
            for(int j = 0; j < k; j++){
                ruter[i][j].settNaboer(ruter,r,k);
            }
        }

        Labyrint enLabyrint = new Labyrint(r, k, ruter);

//Her bruker jeg en metode jeg lagde i ruter, for å gi hver rute en refferanse
//til labyrinten som den er en del av.
        for(int i = 0; i < r; i++){
            for(int j = 0; j < k; j++){
                ruter[i][j].settLabyrint(enLabyrint);
            }
        }

        return enLabyrint;
    }

    public Liste<String> finnUtveiFra(int rad, int kolonne){
        rutenett[rad][kolonne].finnUtvei();
        return veier;
    }
}
