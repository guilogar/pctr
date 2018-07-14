import java.lang.*;
import java.util.*;
import java.util.concurrent.*;

public class algoLamport implements Runnable {
    public static int N = 10;
    public static int[] hilos = new int[N];
    public static int numProcesos = 1;
    private int indice;
    
    public algoLamport(int indice) {
        this.indice = indice;
    }
    
    public void run() {
        while(true) {
            boolean broken = false;
            hilos[this.indice] += 1;
            while(true) {
                for (int j = 0; j < N; j++) {
                    //System.out.println("j => " + j + ", hilos[j] => " + hilos[j]);
                    if(!(hilos[j] != 0 && hilos[this.indice] > hilos[j])) {
                        broken = true;
                        break;
                    }
                }
                Thread.yield();
                if(broken) break;
            }
            Thread.yield();
            
            // Begin Critical section
            numProcesos++;
            numProcesos--;
            System.out.println("Hilo("+this.indice+") => " + numProcesos);
            // End Critical Section
            
            hilos[this.indice] = 0;
        }
    }
    
    public static void main (String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(N);
        for (int i = 0; i < N; i++) {
            pool.execute(new algoLamport(i));
        }
        pool.awaitTermination(1L, TimeUnit.DAYS);
    }
}
