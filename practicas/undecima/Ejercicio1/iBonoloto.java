import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iBonoloto extends Remote {
    
    public boolean comprobarApuesta(int[] apuesta) throws RemoteException;
    public int[] getCombGanadora() throws RemoteException;
}
