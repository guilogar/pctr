import java.util.*;
import java.util.concurrent.*;

public class lectorEscritor {
    
    private boolean lectorLeyendo;
    private boolean escritorEscribiendo;
    
    public lectorEscritor() {
        this.lectorLeyendo = false;
        this.escritorEscribiendo = false;
    }
    
    public synchronized void leerArchivo(String nombreArchivo, int id) {
        while(escritorEscribiendo) {
            try {
                System.err.println("Lo sentimos, pero ahi un escritor escribiendo.");
                wait();
            } catch(Exception e) {
                System.err.println("Error fatal.");
            }
        }
        this.lectorLeyendo = true;
        System.out.println("Leyendo archivo, hilo(" + id + ")");
        System.out.println();
        this.lectorLeyendo = false;
        notifyAll();
    }
    
    public synchronized void escribirArchivo(String nombreArchivo, int id) {
        while(lectorLeyendo) {
            try {
                System.err.println("Lo sentimos, pero ahi un lector leyendo.");
                wait();
            } catch(Exception e) {
                System.err.println("Error fatal.");
            }
        }
        this.escritorEscribiendo = true;
        System.out.println("Escribiendo en el archivo, hilo(" + id + ")");
        System.out.println();
        this.escritorEscribiendo = false;
        notifyAll();
    }
}
