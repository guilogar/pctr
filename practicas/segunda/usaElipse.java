import java.lang.*;
import java.util.*;

public class usaElipse {
    
    public static void main (String[] args) {
        
        // Hago lo que me piden, instancio objetos de la clase y utilizo los metodos.
        
        int[] f1 = {1, 3};
        int[] f2 = {5, 8};
        
        Elipse s = new Elipse();
        Elipse sa = new Elipse(f1, f2);
        
        s.setF1(f1);
        s.setF2(f2);
    }
}
