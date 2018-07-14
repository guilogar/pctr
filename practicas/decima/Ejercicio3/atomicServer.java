import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import java.io.*;
import java.net.*;

public class atomicServer extends Thread {
    private Socket enchufe;
    private static AtomicInteger numVisitas = new AtomicInteger(0);
    
    public atomicServer(Socket s) {
        this.enchufe = s;
    }
    
    public void run() {
        try{
            int num = numVisitas.incrementAndGet();
            System.out.println("Ha tenido " + num + " visitas.");
        } catch(Exception e) {
            System.out.println("Error...");
        }
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
