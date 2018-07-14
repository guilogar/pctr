import java.lang.*;
import java.util.*;

public class UsaHilo {
    
    public static void main(String[] args) throws Exception {
        Hilo p = new Hilo(10000, 0); 
        Hilo q = new Hilo(10000, 1); 
        p.start(); 
        q.start(); 
        p.join(); 
        q.join(); 
        System.out.println(Hilo.getN()); 
    }
}
