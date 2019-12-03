import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

class Monitorer{
    ArrayList<Melding> listeMeldinger = new ArrayList();

    String kryptertMelding;
    String dekryptertMelding;
    Melding melding;
    Telegrafister telegrafister;
    Operasjonsleder leder;

    Lock lås = new ReentrantLock();

    Condition ingenMelding = lås.newCondition();
    Condition alleFerdige = lås.newCondition();

    public Monitorer(){

    }

    public void sendMeldinger(Melding m){
        System.out.println("Melding sendes fra telegrafisten...");
        lås.lock();
        try{
            listeMeldinger.add(m);
            System.out.println("Melding sent av telegrafisten.");
            ingenMelding.signalAll();

        }catch(RuntimeException e){

        }finally{
            lås.unlock();
        }
    }

    public void hentMeldinger(Kryptografer k){
        System.out.println("Melding dekrypteres...");
        lås.lock();
        try{
            while(listeMeldinger.size() == 0 && telegrafister.ferdig == false){
                ingenMelding.await();
            }
            melding = listeMeldinger.get(0); //.remove(0);
            listeMeldinger.remove(0);
            k.melding = melding;

            if(telegrafister.ferdig == true){
                alleFerdige.signalAll();
            }

        } catch (InterruptedException f) {

        }catch(RuntimeException e) {

        }finally{
            lås.unlock();
        }
    }
}
