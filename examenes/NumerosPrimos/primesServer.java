/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author usuario
 */
public class primesServer extends UnicastRemoteObject implements primesInterface {
    
    public primesServer() throws RemoteException
    {
        
    }
    
    public int primesInRange(int beginRange, int endRange) throws RemoteException
    {
        int numPrimos = 0;
        for (int i = beginRange; i < endRange; i++)
        {
            boolean esPrimo = true;
            if(i > 2)
            {
                for (int j = 2; j < i && esPrimo; j++) {
                    System.out.println(i + " % " + j + " == " + (i % j));
                    if(i % j == 0) esPrimo = false;
                }
                
            } else if(i == 2)
            {
                esPrimo = true;
            } else if(i < 2)
            {
                esPrimo = false;
            }
            
            if(esPrimo){
                System.out.println(i + " es primo.");
                numPrimos++;
            }
        }
        
        return numPrimos;
    }
    
    public static void main(String[] args) throws RemoteException, MalformedURLException
    {
        primesServer ps = new primesServer();
        Naming.rebind("NumerosPrimos", ps);
    }
}
