import java.util.*;
import java.util.concurrent.CountDownLatch;
class Test{

    public static void main(String[] args) {
        Operasjonssentral ops = new Operasjonssentral(3);
        Kanal[] kanaler = ops.hentKanalArray();

        CountDownLatch latch = new CountDownLatch(5);

        Monitorer tore = new Monitorer();
        Monitorer sagen = new Monitorer();

        Operasjonsleder ole = new Operasjonsleder(sagen);

        System.out.println(kanaler[0].hentId());
        System.out.println(kanaler[1].hentId());
        System.out.println(kanaler[2].hentId());

        Runnable tele1 = new Telegrafister(tore, kanaler[0]);
        Runnable tele2 = new Telegrafister(tore, kanaler[1]);
        Runnable tele3 = new Telegrafister(tore, kanaler[2]);

        Runnable krypt1 = new Kryptografer(tore, sagen, latch);
        Runnable krypt2 = new Kryptografer(tore, sagen, latch);
        Runnable krypt3 = new Kryptografer(tore, sagen, latch);
        Runnable krypt4 = new Kryptografer(tore, sagen, latch);
        Runnable krypt5 = new Kryptografer(tore, sagen, latch);


        Thread teleJobb1 = new Thread(tele1);
        Thread teleJobb2 = new Thread(tele2);
        Thread teleJobb3 = new Thread(tele3);

        Thread kryptJobb1 = new Thread(krypt1);
        Thread kryptJobb2 = new Thread(krypt2);
        Thread kryptJobb3 = new Thread(krypt3);
        Thread kryptJobb4 = new Thread(krypt4);
        Thread kryptJobb5 = new Thread(krypt5);

        teleJobb1.start();
        teleJobb2.start();
        teleJobb3.start();

        kryptJobb1.start();
        kryptJobb2.start();
        kryptJobb3.start();
        kryptJobb4.start();
        kryptJobb5.start();

        try{
            latch.await();
        }catch(InterruptedException e){}

        ole.filer();

    }
}
