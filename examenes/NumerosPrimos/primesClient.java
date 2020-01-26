/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Naming;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class primesClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        Scanner s = new Scanner(System.in);
        
        System.out.print("Indique el inicio del rango => ");
        int beginR = s.nextInt();
        
        System.out.print("Indique el final del rango => ");
        int endR = s.nextInt();
        
        System.out.print("Indique el numero de subrangos que desea (no mayor al numero de elementos que hay en el rango total) => ");
        int numSubrangos = s.nextInt();
        
        if(numSubrangos > endR - beginR)
        {
            System.out.println("Numero de subrangos mayor que el numero de elementos totales. Saliendo...");
            return;
        }
        
        primesInterface pi = (primesInterface) Naming.lookup("//localhost/NumerosPrimos");
        int ventanaRango = (endR - beginR) / numSubrangos;
        
        int numPrimos = 0;
        int i = 0;
        for (i = beginR; i < endR; i += ventanaRango) {
            if(i + ventanaRango < endR)
                numPrimos += pi.primesInRange(i, i + ventanaRango);
            else
            {
                numPrimos += pi.primesInRange(i, endR + 1);
            }
        }
        
        if(i < endR)
            numPrimos += pi.primesInRange(i, endR - i);
        
        System.out.println("El numero total de primos es => " + numPrimos);
        System.out.println("¡Tenga en cuenta que el número 1 no se toma como nuevo primo!");
    }
    
}
