import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMiAhorcado extends Remote {
    
    /*
     * @param letra
     * @return La posición de la letra o un valor negativo en caso de que no esté.
     */
    public int enviarLetra(char letra)            throws RemoteException;
    public boolean enviarSolucion(String palabra) throws RemoteException;
}
