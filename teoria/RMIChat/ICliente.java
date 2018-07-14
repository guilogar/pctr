import java.rmi.RemoteException;

public interface ICliente {
    public void recibirMensaje(String texto) throws RemoteException;
}
