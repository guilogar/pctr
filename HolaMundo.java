import java.util.Random;

import java.util.*;

public class HolaMundo implements Runnable {
    private int id;
    
    public HolaMundo(int i) {
        this.id = i;
    }
    
    private double generarAleatorio() {
        Random r = new Random();
        return r.nextDouble();
    }
    
    public void run() {
        for (int i = 0; i < 500000; i++) {
            //System.out.println(this.generarAleatorio());
            this.generarAleatorio();
        }
        System.out.println("He terminado con el id => " + this.id);
    }
    
    public static void main (String[] args) {
        
        for (int i = 0; i < 1000; i++) {
            new Thread(new HolaMundo(i)).start();
        }
    }
}
