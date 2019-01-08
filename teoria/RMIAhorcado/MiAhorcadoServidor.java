import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MiAhorcadoServidor extends UnicastRemoteObject implements IMiAhorcado {
    String solucion = "universidad";
    private static final long serialVersionUID = 1;
    
    public MiAhorcadoServidor() throws RemoteException
    {
        solucion = solucion.toLowerCase();
    }
    
    public int enviarLetra(char letra)            throws RemoteException
    {
        return solucion.indexOf(new Character(letra).toString().toLowerCase());
    }
    
    public boolean enviarSolucion(String palabra) throws RemoteException
    {
        return solucion.equals(palabra);
    }
    
    public static void main (String[] args) throws Exception
    {
        IMiAhorcado ORemoto = new MiAhorcadoServidor();
        Naming.rebind("MiAhorcado", ORemoto);
        System.out.println("Servidor MiAhorcado preparado.");
    }
}
