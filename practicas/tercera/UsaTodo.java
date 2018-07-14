import java.lang.*;
import java.util.*;

public class UsaTodo {
    
    public static void main (String[] args) {
        
        Punto[] pu = {new Punto(1, 2), new Punto(2, 6), new Punto(5, 9), new Punto(8, 9)};
        Punto[] po = {new Punto(1, 2), new Punto(2, 6), new Punto(5, 9)};
        
        Triangulo t = new Triangulo(po, 50);
        Poligono p = new Poligono(pu);
        
        //System.out.println(p);
        //System.out.println(t);
    }
}
