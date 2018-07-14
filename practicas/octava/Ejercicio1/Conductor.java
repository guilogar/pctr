import java.util.*;

public class Conductor implements Runnable {
    Conductores badat;
    private int idConductor;
    
    public Conductor(Conductores badat, int idConductor) {
        this.badat = badat;
        this.idConductor = idConductor;
    }
    
    public void run() {
        badat.insertarVehiculo(this.idConductor);
    }
}
