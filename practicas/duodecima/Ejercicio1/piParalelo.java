import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

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
        ExecutorService pool = Executors.newCachedThreadPool();
        int N = 10000;
        pool.execute(new piParalelo(N / 4));
        pool.execute(new piParalelo(N / 4));
        pool.execute(new piParalelo(N / 4));
        pool.execute(new piParalelo(N / 4));
        
        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.DAYS);
        
        System.out.println(getPi(aciertos.get(), N));
    }
}
