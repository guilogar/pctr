import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISuma extends Remote {
    
    public float suma(float a, float b) throws RemoteException;
}
