import java.lang.*;
import java.util.*;

public class matVectorConcurrente implements Runnable {
    
    private static int matriz[][];
    private static int vector[];
    private static int res[];
    private int indice = 0;
    private static boolean finish_mul = false;
    
    public matVectorConcurrente(int i) {
        this.indice = i;
    }
    
    public void run() {
        res[this.indice] = 0;
        for (int i = 0; i < matriz[this.indice].length; i++) {
            res[this.indice] += (matriz[this.indice][i] * vector[i]);
        }
        //System.out.println("Indice => " + this.indice);
        if(this.indice == matriz.length - 1) {
            finish_mul = true;
        }
    }
    
    public static void printVector(int vector[]) {
        for (int i = 0; i < vector.length; i++) {
            System.out.println("| " + vector[i] + " |");
        }
    }
    
    public static void printMatriz(int matriz[][]) {
        for (int i = 0; i < matriz.length; i++) {
            printVector(matriz[i]);
            System.out.println();
        }
    }
    
    public static int[] mulMatrizVector(int matriz[][]) {
        for (int i = 0; i < matriz.length; i++) {
            Thread t = new Thread(new matVectorConcurrente(i));
            t.start();
        }
        
        while(!finish_mul);
        return res;
    }
    
    public static void main (String[] args) {
        int maxNum = 2;
        
        Scanner c = new Scanner(System.in);
        
        System.out.print("Introduzca el numero de filas de la matriz => ");
        int numFilas = c.nextInt();
        System.out.println();
        
        System.out.print("Introduzca el numero de columnas de la matriz => ");
        int numColumnas = c.nextInt();
        System.out.println();
        
        System.out.print("Introduzca el tamaño del vector => ");
        int tamVector = c.nextInt();
        System.out.println();
        
        if(numColumnas != tamVector) {
            System.out.println("No se puede realizar la multiplicación, ya que el numero " +
                               "de columnas y de filas de la matriz y el vector son distintas.");
            return;
        }
        matriz = new int[numFilas][numColumnas];
        vector = new int[tamVector];
        res = new int[numFilas];
        
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                matriz[i][j] = new Random().nextInt(maxNum);
            }
        }
        for (int i = 0; i < tamVector; i++) {
            vector[i] = new Random().nextInt(maxNum);
        }
        
        long start = System.currentTimeMillis();
        mulMatrizVector(matriz);
        long end = System.currentTimeMillis();
        utilsTime.writeInFile("matVectorConcurrente.txt", tamVector + " " + (end-start) + "\n");
        
        //printVector(mulMatrizVector(matriz));
    }
}
