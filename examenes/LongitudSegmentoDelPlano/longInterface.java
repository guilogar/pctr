/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author usuario
 */
public interface longInterface extends Remote {
    
    public float lonSubSegmento(int n, punto[] datos) throws RemoteException;
}
