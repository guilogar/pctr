import java.util.*;
import java.util.concurrent.*;

public class ProcesoA implements Runnable {
    private Buffer f;
    private int tamanio;
    
    public ProcesoA(Buffer f, int tamanio) {
        this.f = f;
        this.tamanio = tamanio;
    }
    
    public void run() {
        double[][] matriz = new double[tamanio][tamanio];
        for (int i = 0; i < this.tamanio; i++) {
            for (int j = 0; j < this.tamanio; j++) {
                matriz[i][j] = new Random().nextInt(100);
            }
        }
        f.insertar(matriz);
    }
}
