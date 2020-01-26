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
public class longServer extends UnicastRemoteObject implements longInterface {
    
    public longServer() throws RemoteException
    {
        
    }
    
    public float lonSubSegmento(int n, punto[] datos) throws RemoteException
    {
        float longitud = (float) 0.0;
        
        for (int i = 1; i < n; i++)
        {
            punto a = datos[i - 1];
            punto b = datos[i];
            longitud += Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
        }
        
        return longitud;
    }
    
    public static void main(String[] args) throws RemoteException, MalformedURLException
    {
        longServer ls = new longServer();
        Naming.rebind("LongServer", ls);
    }
}
