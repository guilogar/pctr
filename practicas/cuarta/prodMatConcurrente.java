import java.lang.*;
import java.util.*;

public class prodMatConcurrente extends Thread {
    
    public static int matrizUno[][];
    public static int matrizDos[][];
    public static int res[][];
    private static boolean resFinish[][];
    private static boolean finish = false;
    private int i = 0;
    private int j = 0;
    
    public prodMatConcurrente(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public void run() {
        mulMatrizMatriz(matrizUno, matrizDos, this.i, this.j);
    }
    
    public void mulMatrizMatriz(int matrizUno[][], int matrizDos[][], int i, int j) {
        if(resFinish == null) {
            resFinish = new boolean[matrizUno.length][matrizDos[i].length];
            for (int p = 0; p < resFinish.length; p++) {
                for (int q = 0; q < resFinish[p].length; q++) {
                    resFinish[p][q] = false;
                }
            }
        }
        res[i][j] = 0;
        for (int h = 0; h < matrizUno[i].length; h++) {
            res[i][j] += (matrizUno[i][h] * matrizDos[h][j]);
        }
        
        resFinish[i][j] = true;
        
        boolean localFinish = true;
        for (int p = 0; p < resFinish.length; p++) {
            for (int q = 0; q < resFinish[p].length; q++) {
                if(!resFinish[p][q]) {
                    localFinish = resFinish[i][j];
                }
            }
        }
        finish = localFinish;
    }
    
    
    public static int[][] getRes() {
        while(!finish);
        return res;
    }
}
