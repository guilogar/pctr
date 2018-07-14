import java.lang.*;
import java.util.*;

public class usaComplejos {
    
    public static void main (String[] args) {
        
        int op = 0;
        Scanner sc = new Scanner(System.in);
        
        Complejos c1 = new Complejos();
        Complejos c2 = new Complejos();
        
        do {
            System.out.println("Menu de opciones");
            System.out.println("================");
            
            System.out.println("1º) Suma.");
            System.out.println("2º) Resta.");
            System.out.println("3º) Producto.");
            System.out.println("4º) División.");
            System.out.println("5º) Salir.");
            System.out.println();
            
            System.out.print("¿Qué desea hacer? => ");
            
            try {
                op = sc.nextInt();
                
                if(op == 5) {
                    System.out.println("Bye.");
                    return;
                }
                
                Scanner s = new Scanner(System.in);
                
                System.out.print("Dime la parte real del primer complejo => ");
                double c1Real = Double.valueOf(s.nextLine());
                c1.setReal(c1Real);
                
                System.out.print("Dime la parte compleja del primer complejo => ");
                double c1Compleja = Double.valueOf(s.nextLine());
                c1.setCompleja(c1Compleja);
                
                System.out.print("Dime la parte real del segundo complejo => ");
                c2.setReal(s.nextDouble());
                
                System.out.print("Dime la parte compleja del segundo complejo => ");
                c2.setCompleja(s.nextDouble());
            } catch(Exception e) {
                System.err.println("Error a la hora de escojer la opción. Vuelva a intentarlo.");
                continue;
            }
            
            switch (op) {
                case 1: {
                    System.out.println();
                    System.out.println("La suma es => " + c1.suma(c2));
                    System.out.println();
                } break;
                case 2: {
                    System.out.println();
                    System.out.println("La resta es => " + c1.resta(c2));
                    System.out.println();
                } break;
                case 3: {
                    System.out.println();
                    System.out.println("El producto es => " + c2.producto(c1));
                    System.out.println();
                } break;
                case 4: {
                    System.out.println();
                    System.out.println("La division es => " + c2.division(c1));
                    System.out.println();
                } break;
                default: {
                    System.out.println();
                    System.out.println("Mala opción. Escoja otra.");
                    System.out.println();
                }
            }
        } while(op != 5);
        
        System.out.println("Bye");
    }
}
