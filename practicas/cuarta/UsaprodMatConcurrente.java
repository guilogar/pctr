import java.lang.*;
import java.util.*;

public class UsaprodMatConcurrente {
    
    
    public static void printVector(int vector[]) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print("| " + vector[i] + " |");
        }
    }
    
    public static void printMatriz(int matriz[][]) {
        for (int i = 0; i < matriz.length; i++) {
            printVector(matriz[i]);
            System.out.println();
        }
    }
    
    public static void main (String[] args) {
        int maxNum = 10;
        
        Scanner c = new Scanner(System.in);
        
        System.out.print("Introduzca el numero de filas de la matriz primera => ");
        int numFilasUno = c.nextInt();
        System.out.println();
        
        System.out.print("Introduzca el numero de columnas de la matriz primera => ");
        int numColumnasUno = c.nextInt();
        System.out.println();
        
        System.out.print("Introduzca el numero de filas de la matriz segunda => ");
        int numFilasDos = c.nextInt();
        System.out.println();
        
        System.out.print("Introduzca el numero de columnas de la matriz segunda => ");
        int numColumnasDos = c.nextInt();
        System.out.println();
        
        
        if(numColumnasDos != numFilasUno) {
            System.out.println("No se puede realizar la multiplicación, ya que el numero " +
                               "de columnas y de filas de las matrices son distintas.");
            return;
        }
        prodMatConcurrente.matrizUno = new int[numFilasUno][numColumnasUno];
        prodMatConcurrente.matrizDos = new int[numFilasDos][numColumnasDos];
        prodMatConcurrente.res = new int[numFilasUno][numColumnasDos];
        
        for (int i = 0; i < numFilasUno; i++) {
            for (int j = 0; j < numColumnasUno; j++) {
                prodMatConcurrente.matrizUno[i][j] = new Random().nextInt(maxNum);
            }
        }
        for (int i = 0; i < numFilasDos; i++) {
            for (int j = 0; j < numColumnasDos; j++) {
                prodMatConcurrente.matrizDos[i][j] = new Random().nextInt(maxNum);
            }
        }
        
        /*
         *printMatriz(prodMatConcurrente.matrizUno);
         *System.out.println();
         *printMatriz(prodMatConcurrente.matrizDos);
         *System.out.println();
         */
        
        for (int i = 0; i < prodMatConcurrente.res.length; i++) {
            for (int j = 0; j < prodMatConcurrente.res[i].length; j++) {
                prodMatConcurrente p = new prodMatConcurrente(i, j);
                p.start();
            }
        }
        
        long start = System.currentTimeMillis();
        prodMatConcurrente.getRes();
        long end = System.currentTimeMillis();
        utilsTime.writeInFile("matProdConcurrente.txt", numFilasUno + " " + (end-start) + "\n");
        
        //printMatriz(prodMatConcurrente.getRes());
    }
}
