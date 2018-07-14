import java.lang.*;
import java.util.*;

public class matVector {
    
    public static void printVector(int vector[]) {
        for (int i = 0; i < vector.length; i++) {
            System.out.println("| " + vector[i] + " |");
        }
    }
    
    public static void printMatriz(int matriz[][]) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("| " + matriz[i][j] + " |");
            }
            System.out.println();
        }
    }
    
    public static int[] mulMatrizVector(int matriz[][], int vector[]) {
        int res[] = new int[matriz.length];
        
        for (int i = 0; i < matriz.length; i++) {
            res[i] = 0;
            for (int j = 0; j < matriz[i].length; j++) {
                res[i] += matriz[i][j] * vector[j];
            }
        }
        
        return res;
    }
    
    public static void main (String[] args) {
        int matriz[][];
        int vector[];
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
        
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                matriz[i][j] = new Random().nextInt(maxNum);
            }
        }
        for (int i = 0; i < tamVector; i++) {
            vector[i] = new Random().nextInt(maxNum);
        }
        
        /*
         *printMatriz(matriz);
         *System.out.println();
         *printVector(vector);
         *System.out.println();
         */
        
        long start = System.currentTimeMillis();
        mulMatrizVector(matriz, vector);
        long end = System.currentTimeMillis();
        utilsTime.writeInFile("matVectorSec.txt", tamVector + " " + (end-start) + "\n");
        
        //printVector(mulMatrizVector(matriz, vector));
    }
}
