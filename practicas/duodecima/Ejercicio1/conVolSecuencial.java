import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import java.util.Scanner;

public class conVolSecuencial implements Runnable
{
    //private static int[][] matriz = new int[10000][10000];
    private static int[][] matriz = new int[10][10];
    private int[][] kernel;
    private int minLimite, maxLimite;
    
    public conVolSecuencial(int[][] kernel, int minLimite, int maxLimite)
    {
        this.kernel = kernel;
        this.minLimite = minLimite;
        this.maxLimite = maxLimite;
    }
    
    @Override
    public void run()
    {
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = minLimite; j < maxLimite && j < matriz[i].length; j++)
            {
                int[][] miniMatriz = new int[kernel.length][kernel.length];
                int centroFila = (kernel.length / 2);
                int centroColumna = (kernel.length / 2);
                miniMatriz[centroFila][centroColumna] = matriz[i][j];
                
                int z = 0, x = 0;
                for (int k = i - centroFila; k <= i + centroFila; k++)
                {
                    for (int p = j - centroColumna; p <= j + centroColumna; p++)
                    {
                        if(k < 0 || p < 0 || k >= matriz.length || p >= matriz[z].length)
                        {
                            miniMatriz[z][x++] = 0;
                        } else
                        {
                            miniMatriz[z][x++] = matriz[k][p];
                        }
                    }
                    z++; x = 0;
                }
                
                int total = 0;
                for (int k = 0; k < kernel.length; k++)
                {
                    for (int p = 0; p < kernel[k].length; p++)
                    {
                        total += (miniMatriz[k][p] * kernel[k][p]);
                    }
                }
                
                matriz[i][j] = total;
                
                /*
                 *for (int k = 0; k < miniMatriz.length; k++)
                 *{
                 *    for (int p = 0; p < miniMatriz.length; p++)
                 *    {
                 *        System.out.print("| " + miniMatriz[k][p] + " |");
                 *    }
                 *    System.out.println();
                 *}
                 */
                /*
                 *for (int k = 0; k < convolucion.length; k++)
                 *{
                 *    for (int p = 0; p < convolucion.length; p++)
                 *    {
                 *        System.out.print("| " + convolucion[k][p] + " |");
                 *    }
                 *    System.out.println();
                 *}
                 */
            }
        }
    }
    
    public static void main (String[] args) throws Exception
    {
        System.out.println("¡Buenas! Bienvenido al programa para hacer convolución.");
        System.out.println("Primero que nada, elija libremente el kernel de convolución que desea aplicar:");
        System.out.println("1º Enfocar");
        System.out.println("2º Realzar Bordes");
        System.out.println("3º Detectar Border");
        System.out.println("4º Filtro de Sobel");
        System.out.println("5º Filtro de Sharpen");
        System.out.print("Su opcion => ");
        
        Scanner s = new Scanner(System.in);
        int op = s.nextInt();
        
        int tareas = 1;
        
        Random r = new Random();
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz[i].length; j++)
            {
                matriz[i][j] = (r.nextInt(40) - 20);
            }
        }
        
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz[i].length; j++)
            {
                System.out.print("| " + matriz[i][j] + " |");
            }
            System.out.println();
        }
        System.out.println("=========================");
        
        ExecutorService es = Executors.newCachedThreadPool();
        
        int[][][] kernels = {
            {
                {0, -1, 0}, {-1, 5, -1}, {0, -1, 0} // Enfoque
            },
            {
                {0, 0, 0}, {-1, 1, 0}, {0, 0, 0} // Realzar bordes
            },
            {
                {0, 1, 0}, {1, -4, 1}, {0, 1, 0} // Detectar bordes
            },
            {
                {-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1} // Filtro Sobel
            },
            {
                {1, -2, 1}, {-2, 5, -2}, {1, -2, 1} // Filtro Sharpen
            },
        };
        int[][] kernel = kernels[0];
        switch (op)
        {
            case 1: {
                kernel = kernels[0];
            } break;
            case 2: {
                kernel = kernels[1];
            } break;
            case 3: {
                kernel = kernels[2];
            } break;
            case 4: {
                kernel = kernels[3];
            } break;
            case 5: {
                kernel = kernels[4];
            } break;
            default: {
                kernel = kernels[0];
                System.out.println("Opcion incorrecta. Fin del programa.");
                System.exit(-1);
            }
        }
        
        int min = 0, max = 0;
        int ventana = matriz.length / tareas;
        for (int i = 0; i < tareas; i++)
        {
             max += ventana;
             es.execute(new conVolSecuencial(kernel, min, max));
             min += ventana;
        }
        es.shutdown();
        while(!es.isTerminated());
        
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz[i].length; j++)
            {
                System.out.print("| " + matriz[i][j] + " |");
            }
            System.out.println();
        }
    }
}

