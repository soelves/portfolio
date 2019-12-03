import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Test {
    public static void main(String[] args) {
        String filnavn = null;

        if (args.length > 0) {
            filnavn = args[0];
        } else {
            System.out.println("FEIL! Riktig bruk av programmet: "
                               +"java Oblig5 <labyrintfil>");
            return;
        }
        File fil = new File(filnavn);
        Labyrint l = null;
        try {
            l = Labyrint.lesFraFil(fil);
            System.out.println(l);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
        }
    }
}
