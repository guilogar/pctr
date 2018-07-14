import java.lang.*;
import java.util.*;
import java.util.concurrent.*;

public class Hyman implements Runnable {
    private int i = 0;
    public static int c1 = 1, c2 = 1, turno = 1, contador = 0;
    
    public Hyman(int i) {
        this.i = i;
    }
    
    public void run() {
        if(i == 1) {
            for (int i = 0; i < 100000; i++) 
                proceso1();
        } else {
            for (int i = 0; i < 100000; i++) 
                proceso2();
        }
    }
    
    void proceso1() {
        c1 = 0;
        while(turno != 1) {
            while(c2 == 0);
            turno = 1;
        }
        // Sección crítica.
        contador++;
        System.out.println(contador);
        c1 = 1;
    }
    
    void proceso2() {
        c2 = 0;
        while(turno != 2) {
            while(c1 == 0);
            turno = 2;
        }
        // Sección crítica.
        contador++;
        System.out.println(contador);
        c2 = 1;
        
    }
    
    public static void main (String[] args) throws InterruptedException {
        
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(new Hyman(1));
        pool.execute(new Hyman(2));
        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.DAYS);
    }
}
