import java.util.*;
import java.util.concurrent.*;

public class Corredor implements Runnable {
    private int id;
    private CyclicBarrier[] barreras;
    private double[] tiempos;
    
    public Corredor(CyclicBarrier[] barreras, int id) {
        this.id = id;
        this.barreras = barreras;
        this.tiempos = new double[3];
    }
    
    public void run() {
        int tiempoAleatorio = new Random().nextInt(1000);
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        
        try {
            Thread.sleep(tiempoAleatorio);
            end = System.currentTimeMillis();
            this.barreras[0].await();
        } catch(Exception e) { }
        this.tiempos[0] = end - start;
        /*
         *System.out.println("He tardado " + this.tiempos[0] + 
         *                   " segundos en llegar a natación. (" + this.id + 
         *                   ")");
         */
        
        start = System.currentTimeMillis();
        try {
            Thread.sleep(tiempoAleatorio);
            end = System.currentTimeMillis();
            this.barreras[1].await();
        } catch(Exception e) { }
        this.tiempos[1] = end - start;
        /*
         *System.out.println("He tardado " + this.tiempos[1] + 
         *                   " segundos en llegar a ciclista. (" + this.id + 
         *                   ")");
         */
        
        start = System.currentTimeMillis();
        try {
            Thread.sleep(tiempoAleatorio);
            end = System.currentTimeMillis();
            this.barreras[2].await();
        } catch(Exception e) { }
        this.tiempos[2] = end - start;
        /*
         *System.out.println("He tardado " + this.tiempos[2] + 
         *                   " segundos en llegar a pie. (" + this.id + 
         *                   ")");
         */
    }
    
    public int getId() {
        return this.id;
    }
    public double getTiempoNatacion() {
        return this.tiempos[0];
    }
    public double getTiempoCiclista() {
        return this.tiempos[1];
    }
    public double getTiempoPie() {
        return this.tiempos[2];
    }
    public double getTiempoTotal() {
        double tiempo = 0;
        for(int i = 0; i < this.tiempos.length; i++) {
            tiempo += this.tiempos[i];
        }
        return tiempo;
    }
}
