import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MiPrimerCliente {
    public static void main (String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        String url = args[0];
        //ISuma servidor = (ISuma) Naming.lookup("//localhost/Sumador");
        ISuma servidor = (ISuma) Naming.lookup(url);
        float resultado = servidor.suma(1, 2);
        System.out.println(resultado);
    }
}
