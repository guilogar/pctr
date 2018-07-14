import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.util.*;

public class sReservas extends UnicastRemoteObject implements iReservas {
    private static final long serialVersionUID = 1L;
    
    public sReservas() throws RemoteException {
        
    }
    
    public Integer[] disponibles() {
        
        return null;
    }
    
    public int hacerReserva(DatosReserva datos) {
        
        return 0;
    }
    
    public void anularReserva(int codigo) {
        System.out.println("Reserva anulada :)");
    }
    
    public static void main (String[] args) throws Exception {
        iReservas r = new sReservas();
        Naming.bind("//localhost:1033/Hotel", r);
        //Naming.bind("Hotel", r);
        System.out.println("Servidor preparado");
    }
}
