import java.io.*;
import java.lang.*;
import java.util.*;
public class Hovedprogram{
    public static void main(String[] args) {
        Graf enGraf = new Graf(args[0]);

        //System.out.println(enGraf);

        //System.out.println(enGraf.loopSjekk(enGraf));


        if(enGraf.loopSjekk(enGraf) == "Ingen loop."){
            enGraf.tidsSkjema(enGraf);
            String s = enGraf.resultat(enGraf);

            Formatter x;
            try{
                x = new Formatter("output.txt");

                x.format("%s", s);

                x.close();
            }catch(Exception e){
                System.out.println("Fikk ikke skrivd til fil.");
            }


            //System.out.println(enGraf.tidsSkjema(enGraf));
            //System.out.println(enGraf);
        }else{
            System.out.println(enGraf.loopSjekk(enGraf));
        }
    }
}
