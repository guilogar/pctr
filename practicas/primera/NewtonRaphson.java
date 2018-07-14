import java.util.*;
import java.lang.*;

public class NewtonRaphson {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Meteme la aproximación => ");
        double aproxInicial = sc.nextDouble();
        
        System.out.print("Meteme el número de iteraciones que quieres => ");
        int numIteraciones = sc.nextInt() + 1;
        
        double[] aproximaciones = new double[numIteraciones];
        aproximaciones[0] = aproxInicial;
        
        for (int i = 1; i < numIteraciones; ++i) {
            aproximaciones[i] = aproximaciones[i-1] - 
                                (
                                 (Math.cos(aproximaciones[i-1]) - Math.pow(aproximaciones[i-1], 3)) / 
                                 (-Math.sin(aproximaciones[i-1]) - (3 * Math.pow(aproximaciones[i-1], 2)))
                                 );
            System.out.println("La aproximación " + i + "º es => " + aproximaciones[i]);
        }
    }
}
