import java.util.*;
import java.util.concurrent.*;

public class Monitor {
    private static final int N = 20;
    private static boolean ocupada[] = new boolean[N];
    private static int impresoras = N;
    
    public synchronized int take_print() {
        while(impresoras == 0) {
            try {
                wait();
            } catch(Exception e) {
                
            }
        }
        int pos = 0;
        while(ocupada[pos]) {
            pos++;
        }
        ocupada[pos] = true;
        impresoras--;
        return pos;
    }
    
    public synchronized void drop_print(int pos) {
        ocupada[pos] = false;
        impresoras++;
        notifyAll();
    }
    
    public static void main (String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        Monitor m = new Monitor();
        
        pool.execute(new Impresora(m));
        for (int i = 1; i < N; i++) {
            pool.execute(new Impresora(m));
        }
        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.DAYS);
    }
}

class Impresora implements Runnable {
    private Monitor m;
    
    public Impresora(Monitor m)
    {
        this.m = m;
    }
    
    public void run() {
        int number_printer = m.take_print();
        System.out.println("Esta imprimiendo por la impresora " + number_printer +
                           " en el hilo " + Thread.currentThread().getName());
    }
}
