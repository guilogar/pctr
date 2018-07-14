
import java.rmi.*;

public interface iReservas extends Remote {
    public Integer[] disponibles() throws RemoteException;
    
    public int hacerReserva(DatosReserva datos) throws RemoteException;
    
    public void anularReserva(int codigo) throws RemoteException;
}
