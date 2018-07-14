import java.util.*;
import java.util.concurrent.*;

public class Semaphore {
    private int contador, hilosBloqueados = 0;
    
    public Semaphore(int cont) {
        this.contador = cont;
    }
    
    public synchronized void Wait() throws InterruptedException {
        if(this.contador > 0) {
            --this.contador;
        } else {
            ++this.hilosBloqueados;
            wait();
        }
    }
    
    public synchronized void Signal() {
        if (this.hilosBloqueados > 0) {
            notify();
            --this.hilosBloqueados;
        } else {
            ++this.contador;
        }
    }
    
    public static void main (String[] args) throws InterruptedException {
        Semaphore mutex = new Semaphore(0);
        Hilo h1 = new Hilo(mutex);
        System.out.println("Lanzando hilos.");
        h1.start();
        System.out.println("Esperando hilos.");
        mutex.Signal();
        h1.join();
        System.out.println("Fin.");
    }
}

class Hilo extends Thread {
    Semaphore mutex;
    
    public Hilo(Semaphore mutex) {
        this.mutex = mutex;
    }
    
    public void run() {
        System.out.println("Hilo " + getName() + " comienza.");
        try {
            mutex.Wait();
        } catch(Exception e) { }
        System.out.println("Hilo " + getName() + " finaliza.");
    }
}
