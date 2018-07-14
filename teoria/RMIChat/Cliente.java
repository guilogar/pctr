import java.rmi.Naming;
import java.rmi.*;
import java.io.*;
import java.util.Scanner;

public class Cliente implements ICliente {
    
    public Cliente() throws Exception {
        
    }
    
    public static void main (String[] args) throws Exception {
        ICliente cliente = new Cliente();
        IRMIChat servidor = (IRMIChat) Naming.lookup("//" + args[0] + "/chat");
        servidor.registrar(cliente);
        
        Scanner teclado = new Scanner(System.in);
        String texto = "";
        while (!texto.equals("salir")) {
            texto = teclado.nextLine();
            servidor.enviarMensaje(texto);
        }
    }
    
    public void recibirMensaje(String texto) throws RemoteException {
        System.out.println(texto);
    }
}
