import java.lang.*;
import java.util.*;

public class ParImpar implements Runnable {
    boolean par;
    
    public ParImpar(boolean par) {
        this.par = par;
    }
    
    public void run() {
        int i = 0;
        if(this.par) {
            i = 1;
        }
        for (i = i; i < 100; i += 2) {
            System.out.println(i);
        }
    }
    
    /*
     *public void start() {
     *    
     *}
     */
    
    public static void main (String[] args) throws Exception {
        Thread hiloPar = new Thread(new ParImpar(true));
        Thread hiloImpar = new Thread(new ParImpar(false));
        
        hiloPar.start();
        hiloImpar.start();
        
        hiloPar.join();
        hiloImpar.join();
        
    }
}
