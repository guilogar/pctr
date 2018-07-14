import java.rmi.Naming;

public class CPiMonteCarlo {
    
    public static void main (String[] args) throws Exception {
        
        iPiMonteCarlo mc = (iPiMonteCarlo) Naming.lookup("//localhost/PiMonteCarlo");
        
        mc.masPuntos(1000);
        System.out.println("Mi aproximación => " + mc.getPi());
        mc.reset();
    }
}
