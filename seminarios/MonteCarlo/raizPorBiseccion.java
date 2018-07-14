import java.lang.*;
import java.util.*;


public class raizPorBiseccion {
    static float f2(float x) {
        return (x * x) - 5;
    }
    
    public static void main (String[] args) {
        float a = 2, b = 5;
        int iteraciones = 0;
        
        try {
            iteraciones = Integer.parseInt(args[0]);
        } catch(Exception e) {
            System.out.println("Debe introducir un numero entero.");
            return;
        }
        
        for (int i = 0; i < iteraciones; ++i) {
            float c = (a + b) / 2;
            
            if (f2(c) == 0) {
                System.out.println("La raíz es => " + c);
            } else {
                if(f2(a) * f2(c) > 0) {
                    a = c;
                } else {
                    b = c;
                }
            }
        }
        System.out.println("La raíz es aproximadamente => " + ((a + b) / 2));
    }
}
