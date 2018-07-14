import java.util.*;
import java.util.concurrent.*;

public class drakkarVikingo {
    private static final int N = 20;
    private int marmita = N;
    
    public synchronized void llenarPlato() {
        while(marmita == 0) {
            try {
                notifyAll();
                wait();
            } catch(Exception e) {
                System.err.println("Error faltal.");
            }
        }
        marmita--;
        System.out.println("Queda en la marmita " + marmita + " raciones.");
    }
    
    public synchronized void llenarMarmita() {
        while(marmita != 0) {
            try {
                wait();
            } catch(Exception e) {
                System.err.println("Error faltal.");
            }
        }
        marmita = N;
        System.out.println("Tiene la marmita " + marmita + " raciones.");
        notifyAll();
    }
    
    public static void main (String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        drakkarVikingo drak = new drakkarVikingo();
        
        pool.execute(new vikingo(0, drak));
        for (int i = 1; i < N; i++) {
            pool.execute(new vikingo(i, drak));
        }
        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.DAYS);
    }
}
class vikingo implements Runnable {
    private int profesion;
    private drakkarVikingo drakkar;
    
    public vikingo(int profesion, drakkarVikingo d) {
        this.profesion = profesion;
        this.drakkar = d;
    }
    
    public void run() {
        if(this.profesion > 0) {
            while(true) {
                this.drakkar.llenarPlato();
                System.out.println("remando...");
            }
        } else {
            while(true) {
                this.drakkar.llenarMarmita();
                System.out.println("rezando...");
            }
        }
    }
}
