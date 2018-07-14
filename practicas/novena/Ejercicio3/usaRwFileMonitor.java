import java.util.*;
import java.util.concurrent.*;

public class usaRwFileMonitor {
    
    public static void main (String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        RWFileMonitor rw = new RWFileMonitor();
        
        for (int i = 0; i < 20; i++) {
            pool.execute(new Hilo(rw, i));
        }
        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.DAYS);
    }
}

class Hilo implements Runnable {
    private RWFileMonitor m;
    private int tipo;
    
    public Hilo(RWFileMonitor m, int tipo) {
        this.m = m;
        this.tipo = tipo;
    }
    
    public void run() {
        if(tipo % 2 == 0) {
            this.m.StartRead();
            String res = utilsFile.readOfFile(".", "holi.txt");
            this.m.EndRead();
            if(res != null) {
                System.out.println("El valor de la lectura es => " + res);
            }
        } else {
            this.m.StartWrite();
            utilsFile.writeInFile(".", "holi.txt", "Oh my gad...\n");
            this.m.EndWrite();
        }
    }
}
