import java.util.*;
import java.util.concurrent.*;

public class usaLectorEscritor {
    private static int N = 20;
    
    public static void main (String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        
        lectorEscritor l = new lectorEscritor();
        
        for (int i = 0; i < N; i++) {
            pool.execute(new Hilo(l, i, "asd.txt"));
        }
        
        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.DAYS);
    }
}

class Hilo implements Runnable {
    private int id;
    private lectorEscritor monitor;
    private String nombreArchivo;
    
    public Hilo(lectorEscritor monitor, int id, String nombreArchivo) {
        this.id = id;
        this.monitor = monitor;
        this.nombreArchivo = nombreArchivo;
    }
    
    public void run() {
        if(id % 2 == 0) {
            this.monitor.leerArchivo(this.nombreArchivo, this.id);
        } else {
            this.monitor.escribirArchivo(this.nombreArchivo, this.id);
        }
    }
}
