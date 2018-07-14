import java.util.*;
import java.util.concurrent.*;

public class ProcesoB implements Runnable {
    private Buffer f1;
    private Buffer f2;
    private int tamTrasp;
    
    public ProcesoB(Buffer f1, Buffer f2, int tamTrasp) {
        this.f1 = f1;
        this.f2 = f2;
        this.tamTrasp = tamTrasp;
    }
    
    public void run() {
        double[][] matriz = f1.extraer();
        double[][] matrizTrasp = new double[this.tamTrasp][tamTrasp];
        
        for (int i = 0; i < this.tamTrasp; i++) {
            for (int j = 0; j < this.tamTrasp; j++) {
                matrizTrasp[i][j] = matriz[j][i];
            }
        }
        f2.insertar(matriz);
    }
}
