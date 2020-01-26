/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Naming;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class longClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        int numPuntos = 20;
        Scanner s = new Scanner(System.in);
        Random r = new Random();
        System.out.println("Escriba ahora las coordenadas de los 20 puntos => ");
        punto[] puntos = new punto[numPuntos];
        
        for (int i = 0; i < numPuntos; i++)
        {
            System.out.print("Escriba la coordenada X de la coordenada " + (i + 1) + " => ");
            //int x = s.nextInt();
            int x = r.nextInt(20);
            
            System.out.print("Escriba la coordenada Y de la coordenada " + (i + 1) + " => ");
            //int y = s.nextInt();
            int y = r.nextInt(20);
            
            puntos[i] = new punto(x, y);
        }
        
        System.out.println("Escriba ahora el numero de subrangos que desea => ");
        int numSubrangos = s.nextInt();
        if(numSubrangos > numPuntos)
        {
            System.err.println("No se puede tener mas subrangos el que numero de puntos maximo");
            return;
        }
        
        longInterface li = (longInterface) Naming.lookup("//localhost/LongServer");
        int min = 0;
        int ventanaRango = numPuntos / numSubrangos;
        int max = ventanaRango;
        
        float lonTotal = 0;
        
        while(max <= numPuntos)
        {
            punto[] punts = new punto[ventanaRango];
            for (int i = 0; i < punts.length; i++)
            {
                punts[i] = puntos[min + i];
            }
            
            lonTotal += li.lonSubSegmento(ventanaRango, punts);
            min = max;
            max += ventanaRango;
        }
        
        if(max > numPuntos)
        {
            max -= ventanaRango;
            punto[] punts = new punto[numPuntos - max];
            for (int i = 0; i < punts.length; i++)
            {
                punts[i] = puntos[min + i];
            }
            lonTotal += li.lonSubSegmento(numPuntos - max, punts);
        }
        
        System.out.println("La longitud total es => " + lonTotal);
    }
    
}
