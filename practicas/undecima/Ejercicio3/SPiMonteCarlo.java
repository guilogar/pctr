import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SPiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo {
    private static final long serialVersionUID = 1;
    private int puntos;
    
    public SPiMonteCarlo() throws RemoteException {
        this.puntos = 1000;
    }
    
    public void masPuntos(int puntos) {
        this.puntos += puntos;
    }
    
    public void reset() {
        this.puntos = 0;
    }
    
    public double getPi() {
        int aciertos = 0;
        for (int i = 0; i < this.puntos; i++) {
            double x = Math.random() * 2 - 1;
            double y = Math.random() * 2 - 1;
            double z = x*x + y*y;
            
            if(z <= 1) {
                aciertos++;
            }
        }
        
        return (aciertos * 4) / this.puntos;
    }
    
    public static void main (String[] args) throws Exception {
        iPiMonteCarlo l = new SPiMonteCarlo();
        Naming.bind("PiMonteCarlo", l);
        System.out.println("Servidor para la aproximacion de PI preparado.");
    }
}
