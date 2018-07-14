import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iLibros extends Remote {
    
    public void insertarLibro(Libro l) throws RemoteException;
    public boolean eliminarLibro(Libro l) throws RemoteException;
    public Libro eliminarLibro(int indice) throws RemoteException;
    public Libro consultarLibro(Libro l) throws RemoteException;
    public Libro consultarLibro(String nombre) throws RemoteException;
    public Libro consultarLibro(String nombre, String siglas) throws RemoteException;
    public double numerosLibrosBiblioteca() throws RemoteException;
}
