import java.lang.*;
import java.util.*;

public class Incremento extends Thread {
//public class Incremento implements Runnable {
    
    static int x = 0;
    
    public void run() {
        for (int i = 0; i < 10000; ++i)
            ++x;
    }
    
    /*
     *public void start() {
     *    
     *}
     *
     *public void join() {
     *    
     *}
     */
    
    public static void main (String[] args) throws InterruptedException {
        Incremento hilo1 = new Incremento();
        Incremento hilo2 = new Incremento();
        hilo1.start();
        hilo2.start();
        hilo1.join();
        hilo2.join();
        System.out.println(hilo1.x);
    }
}
