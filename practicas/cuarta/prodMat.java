import java.lang.*;
import java.util.*;

public class prodMat {
    
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
    
    public static int[][] mulMatrizMatriz(int matrizUno[][], int matrizDos[][]) {
        int res[][] = new int[matrizUno.length][matrizDos[0].length];
        for (int i = 0; i < matrizUno.length; i++) {
            for (int h = 0; h < matrizDos[i].length; h++) {
                res[i][h] = 0;
                for (int j = 0; j < matrizDos[i].length; j++) {
                    res[i][h] += (matrizUno[i][j] * matrizDos[j][h]);
                }
            }
        }
        
        return res;
    }
    
    public static void main (String[] args) {
        int matrizUno[][];
        int matrizDos[][];
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
        matrizUno = new int[numFilasUno][numColumnasUno];
        matrizDos = new int[numFilasDos][numColumnasDos];
        
        for (int i = 0; i < numFilasUno; i++) {
            for (int j = 0; j < numColumnasUno; j++) {
                matrizUno[i][j] = new Random().nextInt(maxNum);
            }
        }
        for (int i = 0; i < numFilasDos; i++) {
            for (int j = 0; j < numColumnasDos; j++) {
                matrizDos[i][j] = new Random().nextInt(maxNum);
            }
        }
        
        /*
         *printMatriz(matrizUno);
         *System.out.println();
         *printMatriz(matrizDos);
         *System.out.println();
         */
        
        long start = System.currentTimeMillis();
        mulMatrizMatriz(matrizUno, matrizDos);
        long end = System.currentTimeMillis();
        utilsTime.writeInFile("matProd.txt", numFilasUno + " " + (end-start) + "\n");
        
        //printMatriz(mulMatrizMatriz(matrizUno, matrizDos));
    }
}
