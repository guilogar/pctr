import java.util.*;
import java.util.concurrent.*;

public class Reentrancia extends Thread {
    
    void metodo(int iteraciones) {
        synchronized(this) {
            System.out.println("Iteraciones: " + iteraciones);
            if(iteraciones > 0)
                metodo(--iteraciones);
        }
    }
    
    public void run() {
        metodo(5);
    }
    
    public static void main (String[] args) throws InterruptedException {
        Reentrancia h1 = new Reentrancia();
        h1.start();
        h1.join();
    }
}
