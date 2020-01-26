/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author usuario
 */
public class filtroMedio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        int Cb = 0; // Tarea de calculo con division del trabajo, coeficiente de bloqueo igual a 0
        
        Scanner s = new Scanner(System.in);
        System.out.print("Introduzca la dimension de la Matriz => ");
        int N = s.nextInt();
        
        if(N <= 0)
        {
            System.out.println("La dimension no puede ser negativa. Saliendo...");
            return;
        }
        
        System.out.print("Introduzca el numero de tareas paralelas => ");
        int M = s.nextInt();
        
        if(M <= 0 || M > N)
        {
            System.out.println("El numero de tareas es incorrecto. Tiene que ser mayor que 0 y menor o igual que la dimension de la matriz. Saliendo...");
            return;
        }
        
        int tareas = M / (1 - Cb);
        int tamFiltro = 1;
        
        System.out.println("Desea cargar la reticula de forma manual o de forma aleatoria => ");
        System.out.println("1) Manual.");
        System.out.println("2) Aleatoria.");
        System.out.print("Su opción => ");
        
        Reticula r = new Reticula(N, tamFiltro);
        
        int op = s.nextInt();
        
        long start = 0;
        switch(op)
        {
            case 1: {
                r.introducirManualmente();
                
                System.out.println("Matriz Original");
                System.out.println("===============");
                
                r.enseniarReticula();
                
                start = System.nanoTime();
                r.getFiltroMedio(tareas);
                
                System.out.println("Matriz Filtro Medio");
                System.out.println("===================");
                
                r.enseniarReticulaJ();
            }     break;
            case 2: {
                start = System.nanoTime();
                r.introducirAleatorio(tareas);
            } break;
            default: {
                start = System.nanoTime();
                r.introducirAleatorio(tareas);
            }
        }
        long end = System.nanoTime();
        
        long time = end - start;
        System.out.println("Ha tardado la ejecución " + time + " microsegundos.");
    }
    
}

class Reticula
{
    public int[][] ret;
    public double[][] retJ;
    private int tamFiltro;
    
    public Reticula(int n, int tamFiltro)
    {
        this.ret = new int[n][n];
        this.retJ = new double[n][n];
        this.tamFiltro = tamFiltro;
    }
    
    public void introducirManualmente()
    {
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < this.ret.length; i++) {
            for (int j = 0; j < this.ret[i].length; j++) {
                System.out.print("Introduzca un valor entre 0 y 255, ambos inclusives (" + i + ", " + j +") => ");
                this.ret[i][j] = s.nextInt();
            }
        }
        
        System.out.println("Listo calisto. Matriz rellena.");
    }
    
    public void introducirAleatorio(int tareas) throws InterruptedException
    {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(tareas, tareas, 60000L, TimeUnit.DAYS, new LinkedBlockingQueue<Runnable> ());
        
        int ventana = this.ret.length / tareas;
        
        int i = 0;
        for (i = 0; i < this.ret.length; i += ventana) {
            tpe.submit(new Rellenar(this.ret, i, i + ventana));
        }
        
        if(i < this.ret.length)
            tpe.submit(new Rellenar(this.ret, i, this.ret.length - i));
        
        tpe.shutdown();
        tpe.awaitTermination(60000L, TimeUnit.DAYS);
    }
    
    public double[][] getFiltroMedio(int tareas) throws InterruptedException
    {   
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(tareas, tareas, 60000L, TimeUnit.DAYS, new LinkedBlockingQueue<Runnable> ());
        
        int ventana = this.retJ.length / tareas;
        
        int i = 0;
        for (i = 0; i < this.retJ.length; i += ventana) {
            tpe.submit(new RellenarFiltro(this.ret, this.retJ, i, i + ventana, this.tamFiltro));
        }
        
        if(i < this.retJ.length)
            tpe.submit(new RellenarFiltro(this.ret, this.retJ, i, retJ.length - i, this.tamFiltro));
        
        tpe.shutdown();
        tpe.awaitTermination(60000L, TimeUnit.DAYS);
        
        return this.retJ;
    }
    
    public void enseniarReticula()
    {
        for (int i = 0; i < this.ret.length; i++) {
            for (int j = 0; j < this.ret[i].length; j++) {
                System.out.print("| " + this.ret[i][j] + " |");
            }
            System.out.println("");
        }
    }
    
    public void enseniarReticulaJ()
    {
        for (int i = 0; i < this.retJ.length; i++) {
            for (int j = 0; j < this.retJ[i].length; j++) {
                System.out.print("| " + this.retJ[i][j] + " |");
            }
            System.out.println("");
        }
    }
}

class Rellenar implements Runnable
{
    private int[][] ret;
    private int inicio, fin;
    
    public Rellenar(int[][] ret, int inicio, int fin)
    {
        this.ret = ret;
        this.inicio = inicio;
        this.fin = fin;
    }
    
    @Override
    public void run()
    {
        Random r = new Random();
        for (int i = this.inicio; i < this.fin; i++) {
            for (int j = 0; j < this.ret[i].length; j++) {
                this.ret[i][j] = r.nextInt(255);
                //this.ret[i][j] = 1;
            }
        }
    }
}

class RellenarFiltro implements Runnable
{
    private int[][] retOrigen;
    private double[][] retFiltro;
    private int inicio, fin, tamFiltro;
    
    public RellenarFiltro(int[][] retOrigen, double[][] retFiltro, int inicio, int fin, int tamFiltro)
    {
        this.retOrigen = retOrigen;
        this.retFiltro = retFiltro;
        this.inicio = inicio;
        this.fin = fin;
        this.tamFiltro = tamFiltro;
    }
    
    @Override
    public void run()
    {
        Random r = new Random();
        for (int i = this.inicio; i < this.fin; i++) {
            for (int j = 0; j < this.retFiltro[i].length; j++) {
                int sumatorio = 0;
                for (int k = -this.tamFiltro; k < this.tamFiltro; k++) {
                    for (int l = -this.tamFiltro; l < this.tamFiltro; l++) {
                        int p = i + k;
                        int q = j + l;
                        
                        if(p < 0 || p >= this.retOrigen.length || q < 0 || q >= this.retOrigen.length)
                            sumatorio += 0;
                        else
                            sumatorio += this.retOrigen[i + k][j + l];
                    }
                }
                double res = (double) (1 / Math.pow(2 * this.tamFiltro + 1, 2)) * sumatorio;
                this.retFiltro[i][j] = res;
            }
        }
    }
}
