import java.util.*;
class Telegrafister implements Runnable{
    public Monitorer tore;
    public Kanal kanal;
    static int teller = 0;
    static public boolean ferdig = false;


    Melding melding;

    public Telegrafister(Monitorer m, Kanal k){
        tore = m;
        kanal = k;

    }

    public void run(){
        System.out.println("Telegrafisten jobber...");
        String kryptertMelding = kanal.lytt();
        try{
            while(kryptertMelding != null){
                melding = new Melding(kryptertMelding, kanal);
                tore.sendMeldinger(melding);

                System.out.println("Melding levert.");
                kryptertMelding = kanal.lytt();

            }
        } catch(RuntimeException e){

        }finally{
            teller++;
            if(teller == 3){
                System.out.println("Hei");
                ferdig = true;
            }
        }
        System.out.println("Telegrafisten er ferdig.");


    }
}
