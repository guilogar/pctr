import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class RMIChat extends UnicastRemoteObject implements IRMIChat {
    private static final long serialVersionUID = 42L;
    Vector<ICliente> listClientes = new Vector<ICliente>();
    
    public RMIChat() throws RemoteException {
        
    }
    
    public void enviarMensaje(String mensaje) throws RemoteException {
        for (int i = 0; i < listClientes.size(); i++) {
            listClientes.get(i).recibirMensaje(mensaje);
        }
    }
    
    public void registrar(ICliente cliente) throws RemoteException {
        listClientes.add(cliente);
    }
    
    // Main
    public static void main (String[] args) throws Exception {
        RMIChat chat = new RMIChat();
        Naming.rebind("//localhost/chat", chat);
        System.out.println("Servdor preparado.");
    }
}
