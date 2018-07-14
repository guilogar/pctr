import java.lang.*;
import java.util.*;

public class aleatorios {
    
    public static void main (String[] args) {
        Scanner n = new Scanner(System.in);
        
        System.out.print("Introduzca cuanto numeros quiere que se generen => ");
        int num = n.nextInt();
        
        if(num <= 0) {
            System.out.println("Seria ideal que al menos se generara un numero.");
            return;
        }
        for (int i = 0; i < num; ++i) {
            System.out.println( (i+1) + "º) " + Math.random());
        }
    }
}
