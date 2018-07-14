import java.lang.*;
import java.util.*;

public class Ack {
    
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Introduce m => ");
        double m = sc.nextDouble();
        
        System.out.print("Introduce n => ");
        double n = sc.nextDouble();
        
        double res = ackermann(m, n);
        System.out.println("El resultado de la convinación de " + m + " y " + n + " es => " + res);
    }

    public static double ackermann(double m, double n){
        if(m == 0){
            return (n + 1);
        } else if(m > 0 && n == 0){
            return ackermann(m - 1, 1);
        } else{
            return ackermann(m - 1, ackermann(m, n - 1));
        }
    }
}
