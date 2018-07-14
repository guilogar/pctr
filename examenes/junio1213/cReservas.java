import java.rmi.*;

public class cReservas {
    
    public static void main (String[] args) throws Exception {
        iReservas r = (iReservas) Naming.lookup("//localhost:1033/Hotel");
        //iReservas r = (iReservas) Naming.lookup("//localhost/Hotel");
        r.anularReserva(5);
    }
}
