import java.lang.*;
import java.util.*;

public class Estadistica {
    
    public static void main (String[] args) {
        
        int n, op = 0;
        try {
            n = Integer.parseInt(args[0]);
        } catch(Exception e) {
            System.err.println("Numero de valores a introducir incorrectos. Fin del programa.");
            return;
        }
        double[] numeros = new double[n];
        
        if(n <= 0) {
            System.out.println("Se necesita al menos que se pueda introducir 1 valor numerico.");
            return;
        }
        
        try {
            for (int i = 0; i < n; i++) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Introduzca el " + (i + 1) + "º valor numerico => ");
                numeros[i] = sc.nextDouble();
            }
        } catch(Exception e) {
            System.err.println("Error encontrado en los valores numericos. Vuelva a intentarlo.");
            return;
        }
        
        do {
        
            System.out.println();
            
            System.out.println("Opciones");
            System.out.println("========");
            System.out.println("1º) Media.");
            System.out.println("2º) Moda.");
            System.out.println("3º) Varianza.");
            System.out.println("4º) Desviación Típica.");
            System.out.println("5º) Salir.");
            
            System.out.println();
            
            System.out.print("¿Qué desea hacer? => ");
            
            try {
                Scanner sc = new Scanner(System.in);
                op = sc.nextInt();
            } catch(Exception e) {
                System.out.println();
                System.out.println("Error, parametro no admitido. Vuelva a intentarlo.");
                System.out.println();
                continue;
            }
            
            switch (op) {
                case 1: {
                    double media = getMedia(numeros);
                    System.out.println("La media aritmetica es => " + media);
                } break;
                case 2: {
                    double moda = getModa(numeros);
                    System.out.println("La moda es => " + moda);
                } break;
                case 3: {
                    double varianza = getVarianza(numeros);
                    System.out.println("La varianza es => " + varianza);
                } break;
                case 4: {
                    double desvTipica = getVarianza(numeros);
                    desvTipica = Math.sqrt(desvTipica);
                    System.out.println("La desviación típica es => " + desvTipica);
                } break;
                case 5: {
                    System.out.println("Bye.");
                    return;
                }
                default: {
                    System.out.println("Opción Invalida. Vuelva a intentarlo.");
                }
            }
            
        } while(op != 5);
    }
    
    public static double getMedia(double[] numeros) {
        double media = 0;
        for (int i = 0; i < numeros.length; i++) {
            media += numeros[i];
        }
        return (media / numeros.length);
    }
    
    public static double getModa(double[] numeros) {
        double moda = 0, contador = 0;
        
        for (int i = 0; i < numeros.length; ++i) {
            double count = 0;
            for (int j = 0; j < numeros.length; ++j) {
                if (numeros[j] == numeros[i]) ++count;
            }
            if (count > contador) {
                contador = count;
                moda = numeros[i];
            }
        }
        return moda;
    }
    
    public static double getVarianza(double[] numeros) {
        double varianza = 0;
        
        for (int i = 0; i < numeros.length; i++) {
            varianza += (numeros[i] - getMedia(numeros));
        }
        varianza = Math.pow(varianza, 2);
        varianza = varianza / (numeros.length - 1);
        
        return varianza;
    }
}
