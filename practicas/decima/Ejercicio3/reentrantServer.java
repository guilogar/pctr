import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import java.io.*;
import java.net.*;

public class reentrantServer extends Thread {
    private Socket enchufe;
    private static int numVisitas = 0;
    private ReentrantLock l;
    
    public reentrantServer(Socket s) {
        this.enchufe = s;
        this.l = new ReentrantLock();
    }
    
    public void run() {
        this.l.lock();
        
        try {
            System.out.println("Ha tenido " + (++numVisitas) + " visitas.");
        } catch(Exception e) {
            System.out.println("Error...");
        }
        
        this.l.unlock();
    }
    
    public static void main (String[] args) throws InterruptedException {
        int puerto = 2001;
        
        ExecutorService pool = Executors.newCachedThreadPool();
        
        try{
            ServerSocket chuff = new ServerSocket (puerto, 3000);
            while (true){
                System.out.println("Esperando solicitud de conexion...");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexion...");
                pool.execute(new atomicServer(cable));
            }
        } catch (Exception e) {System.out.println("Error en sockets...");}
        
        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.DAYS);
    }
}
