import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import java.util.Scanner;

public class conVolParalela implements Runnable
{
    //private static int[][] matriz = new int[10000][10000];
    private static int[][] matriz = new int[10][10];
    private int[][] kernel;
    private int minLimite, maxLimite;
    
    public conVolParalela(int[][] kernel, int minLimite, int maxLimite)
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
        
        System.out.print("Elija el numero de tareas para el procesamiento => ");
        int tareas = s.nextInt();
        
        s.reset();
        System.out.print("¿Desea usar un matriz de prueba o una dinámica? (prueba/dinamica) (p/d) => ");
        String eleccion = s.next();
        
        int[][] matriz_prueba =
        {
            {-14, 5  , -5 , 5  , -11, -15, 7  , 18 , -6 , -12}, // 1
            {-8 , -3 , 17 , -5 , 5  , -11, -2 , -9 , -18, 3  }, // 2
            {2  , 4  , -4 , 4  , 6  , 2  , 16 , 1  , 6  , -8 }, // 3
            {-5 , 4  , 9  , 2  , -18, 19 , 0  , 9  , 9  , 6  }, // 4
            {9  , -19, -17, -1 , -12, 8  , 0  , 7  , -20, -6 }, // 5
            {-18, -6 , 19 , -2 , 10 , -3 , 13 , 6  , 18 , -1 }, // 6
            {11 , -15, -17, 0  , 0  , 17 , -9 , -20, -2 , -20}, // 7
            {6  , -12, 13 , 10 , 7  , -18, -10, -13, 1  , -18}, // 8
            {2  , 3  , -4 , -7 , 14 , 19 , 2  , 7  , 5  , -8 }, // 9
            {-14, -12, -2 , -19, 9  , 10 , 11 , 12 , 10 , -11}, // 10
        };
        
        Random r = new Random();
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz[i].length; j++)
            {
                if(eleccion.equals("prueba") || eleccion.equals("p"))
                {
                    matriz[i][j] = matriz_prueba[i][j];
                } else
                {
                    matriz[i][j] = (r.nextInt(40) - 20);
                }
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
        
        int min = 0, max = 0, ventana = matriz.length / tareas;
        for (int i = 0; i < tareas; i++)
        {
             max += ventana;
             es.execute(new conVolParalela(kernel, min, max));
             min += ventana;
        }
        
        if(max < matriz.length)
             es.execute(new conVolParalela(kernel, min, matriz.length));
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

