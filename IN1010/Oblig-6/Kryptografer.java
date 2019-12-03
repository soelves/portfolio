import java.util.concurrent.CountDownLatch;
class Kryptografer implements Runnable{
    private Monitorer motta;
    private Monitorer send;
    String kryptertMelding;
    String dekryptertMelding;
    Melding melding;
    Telegrafister telegrafister;
    CountDownLatch latch;

    public Kryptografer(Monitorer m1, Monitorer m2, CountDownLatch l){
        motta = m1;
        send = m2;
        latch = l;
    }

    public void run(){
        System.out.println("Kryptografen jobber...");
        try{

            while(telegrafister.ferdig != true || motta.listeMeldinger.size() > 0){
                motta.hentMeldinger(this);
                kryptertMelding = melding.giMelding();
                dekryptertMelding = Kryptografi.dekrypter(kryptertMelding);
                melding.endreMelding(dekryptertMelding);
                send.listeMeldinger.add(melding);

            }
        } catch(RuntimeException e){}


        finally{
            System.out.println("Kryptografen er ferdig.");
            latch.countDown();
        }


    }
}
