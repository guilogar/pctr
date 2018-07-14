import java.lang.*;
import java.util.*;

public class speedUp {
    
    public static void printVector(int vector[]) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print("| " + vector[i] + " |");
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
    
    public static void main (String[] args) throws InterruptedException {
        int matriz[][];
        int vector[][];
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
        vector = new int[tamVector][1];
        
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                matriz[i][j] = new Random().nextInt(maxNum);
            }
        }
        for (int i = 0; i < tamVector; i++) {
            vector[i][0] = new Random().nextInt(maxNum);
        }
        
        long startSec = System.currentTimeMillis();
        //System.out.println("Res");
        //printMatriz(new matVector().mulMatrizVector(matriz, vector));
        //System.out.println("---");
        new matVector().mulMatrizVector(matriz, vector);
        long endSec = System.currentTimeMillis();
        long timeSec = endSec - startSec;
        
        matVectorConcurrente.matriz = matriz;
        matVectorConcurrente.vector = vector;
        matVectorConcurrente.res = new int[tamVector][1];
        
        int tareas = Runtime.getRuntime().availableProcessors();
        for (int i = 1; i <= tareas; i++) {
            long startConcu = System.currentTimeMillis();
            //System.out.println("Res");
            //printMatriz(matVectorConcurrente.mulMatrizVector(matriz, i));
            //System.out.println("---");
            matVectorConcurrente.mulMatrizVector(matriz, i);
            long endConcu = System.currentTimeMillis();
            long timeConcu = endConcu - startConcu;
            double speedUpTime = (timeConcu == 0) ? 0 : (double) (timeSec / timeConcu);
            utilsFile.writeInFile("info", "speed_up.txt", "" + i + " " + speedUpTime + "\n");
        }
    }
}
