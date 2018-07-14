import java.util.*;
import java.util.concurrent.*;

public class Conductores {
    private int maxNumVehiculos;
    private int numVehiculos;
    
    public Conductores(int maxNumVehiculos) {
        this.maxNumVehiculos = maxNumVehiculos;
        this.numVehiculos = 0;
    }
    
    public synchronized void insertarVehiculo(int idConductor) {
        while(this.numVehiculos == this.maxNumVehiculos) {
            try {
                System.out.println("No se puede añadir, limite alcanzado según la DGT.");
                System.out.println("Abriendo sitio en la base de datos a la fuerza...");
                truncateBadat();
                wait();
            } catch(Exception e) {
                System.err.println("Error fatal.");
            }
        }
        numVehiculos++;
        System.out.println("Nuevo vehiculo con id " + idConductor + ", incorporado a la DGT.");
    }
    
    public synchronized void borrarVehiculo() {
        while(this.numVehiculos == 0) {
            try {
                notifyAll();
                wait();
            } catch(Exception e) {
                System.err.println("Error fatal.");
            }
        }
        numVehiculos--;
        System.out.println("Un vehiculo a sido retirado de la circulación según la DGT.");
    }
    
    public synchronized void truncateBadat() {
        while(this.numVehiculos != this.maxNumVehiculos) {
            try {
                wait();
            } catch(Exception e) {
                System.err.println("Error fatal.");
            }
        }
        this.numVehiculos = 0;
        notifyAll();
    }
    
    public synchronized int getNumConductores() {
        return this.numVehiculos;
    }
}
