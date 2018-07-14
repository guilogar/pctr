import java.util.*;
import java.util.concurrent.*;

public class ProcesoC implements Runnable {
    private Buffer f;
    
    public ProcesoC(Buffer f) {
        this.f = f;
    }
    
    public void run() {
        double[][] matriz = f.extraer();
        double producto = 1;
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(i == j) {
                    producto *= matriz[i][j];
                }
            }
        }
        System.out.println("Y el producto de su diagonal principal es => " + producto);
    }
}
