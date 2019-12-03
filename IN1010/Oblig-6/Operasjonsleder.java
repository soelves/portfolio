import java.io.*;
import java.lang.*;
import java.util.*;
class Operasjonsleder{
    Monitorer sagen;
    ArrayList<Melding> liste;
    ArrayList<Melding> liste1 = new ArrayList();
    ArrayList<Melding> liste2 = new ArrayList();
    ArrayList<Melding> liste3 = new ArrayList();

    public Operasjonsleder(Monitorer m){
        sagen = m;
        liste = sagen.listeMeldinger;
    }
    PrintWriter fil1 = null;
    PrintWriter fil2 = null;
    PrintWriter fil3 = null;

    public void filer(){

        for (int i = 0; i < liste.size(); i++){
            if(liste.get(i).hentKanalID() == 1){
                liste1.add(liste.get(i));
            }

            if(liste.get(i).hentKanalID() == 2){
                liste2.add(liste.get(i));
            }

            if(liste.get(i).hentKanalID() == 3){
                liste3.add(liste.get(i));
            }
        }
        Collections.sort(liste1);
        Collections.sort(liste2);
        Collections.sort(liste3);

        try{
            fil1 = new PrintWriter("oblig6-fil1.txt", "utf-8");
        } catch(UnsupportedEncodingException f){

        } catch(FileNotFoundException e){}

        try{
            fil2 = new PrintWriter("oblig6-fil2.txt", "utf-8");
        } catch(UnsupportedEncodingException f){

        } catch(FileNotFoundException e){}

        try{
            fil3 = new PrintWriter("oblig6-fil3.txt", "utf-8");
        } catch(UnsupportedEncodingException f){

        } catch(FileNotFoundException e){}

        for(int i = 0; i < liste1.size(); i++){
            fil1.println(liste1.get(i).giMelding());
            fil1.println();
            fil1.println();
        }

        for(int i = 0; i < liste2.size(); i++){
            fil2.println(liste2.get(i).giMelding());
            fil2.println();
            fil2.println();
        }

        for(int i = 0; i < liste3.size(); i++){
            fil3.println(liste3.get(i).giMelding());
            fil3.println();
            fil3.println();
        }

        fil1.close();
        fil2.close();
        fil3.close();
    }
}
