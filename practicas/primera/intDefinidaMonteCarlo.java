import java.lang.*;
import java.util.*;

public class intDefinidaMonteCarlo {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("¿Cuantas iteraciones deseas? (=< 65535) => ");
        int numeroPuntos = sc.nextInt();
        int area = 0;
        
        for (int i = 0; i < numeroPuntos; ++i) {
            if(Math.random() < Math.sin(i)) {
                area++;
            }
        }
        
        if (area != 0) {
            System.out.println("El area de la integral es => " + (double) area / numeroPuntos);
        }
    }
}
