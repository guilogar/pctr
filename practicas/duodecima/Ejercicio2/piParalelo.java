import java.util.concurrent.atomic.AtomicInteger;

import java.util.*;
import java.util.concurrent.*;

public class piParalelo implements Runnable {
    private int puntos;
    public static AtomicInteger aciertos = new AtomicInteger();
    
    public piParalelo(int puntos) {
        this.puntos = puntos;
    }
    
    public void masPuntos(int puntos) {
        this.puntos += puntos;
    }
    
    public void reset() {
        this.puntos = 0;
    }
    
    public synchronized void run() {
        for (int i = 0; i < this.puntos; i++) {
            double x = Math.random() * 2 - 1;
            double y = Math.random() * 2 - 1;
            double z = x*x + y*y;
            
            if(z <= 1) {
                aciertos.incrementAndGet();
            }
        }
    }
    
    public static double getPi(int aciertos, int puntos) {
        return (double) (aciertos * 4) / puntos;
    }
    
    public static void main (String[] args) throws Exception {
        int N = 10000;
        
        // Tiempo con una sola hebra.
        // ==========================
        
        Thread t = new Thread(new piParalelo(N));
        long startUnaHebra = System.currentTimeMillis();
        t.start();
        t.join();
        long endUnaHebra = System.currentTimeMillis();
        long tiempoUnaHebra = endUnaHebra-startUnaHebra;
        utilsFile.writeInFile("info", "java_tiempo.txt", "1 " + tiempoUnaHebra + "\n");
        utilsFile.writeInFile("info", "java_speed_up.txt", "1 " + (tiempoUnaHebra/tiempoUnaHebra) + "\n");
        
        aciertos.set(0);
        
        // Tiempo con multihebra.
        // ==========================
        
        for (int i = 2; i < 8; i++) {
            ExecutorService pool = Executors.newCachedThreadPool();
            
            for (int j = 0; j < i; j++) {
                pool.execute(new piParalelo(N / i));
            }
            
            long start = System.currentTimeMillis();
            pool.shutdown();
            pool.awaitTermination(1L, TimeUnit.DAYS);
            long end = System.currentTimeMillis();
            double tiempoMultiHebra = end-start;
            
            utilsFile.writeInFile("info", "java_tiempo.txt", i + " " + tiempoMultiHebra + "\n");
            if(tiempoMultiHebra == 0) {
                tiempoMultiHebra = tiempoUnaHebra / 10;
            }
            System.out.println("Tiempo con " + i + "hebras => " + tiempoMultiHebra);
            utilsFile.writeInFile("info", "java_speed_up.txt", i + " " + (tiempoUnaHebra/tiempoMultiHebra) + "\n");
            
            System.out.println("Aproximacion de pi con " + i + " hebras => " + getPi(aciertos.get(), N));
            aciertos.set(0);
        }
    }
}
