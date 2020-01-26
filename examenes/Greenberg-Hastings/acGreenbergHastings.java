/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author usuario
 */
public class acGreenbergHastings {
    
    private static Estados[][] reticula;
    
    public static void rellenarManual()
    {
        System.out.println("1 = Estable, 2 = Excitado, 3 = Refractario");
        Scanner s = new Scanner(System.in);
        
        for (int i = 0; i < reticula.length; i++)
        {
            for (int j = 0; j < reticula[i].length; j++)
            {
                System.out.println("¿Que valor quiere para la casilla " + i + ", " + j + " ?");
                int valor = s.nextInt();
                
                switch(valor)
                {
                    case 1: reticula[i][j] = Estados.Estable; break;
                    case 2: reticula[i][j] = Estados.Excitado; break;
                    case 3: reticula[i][j] = Estados.Refractario; break;
                    default: reticula[i][j] = Estados.Estable;
                }
            }
        }
    }
    
    public static void enseniarReticula()
    {
        for (int i = 0; i < reticula.length; i++)
        {
            for (int j = 0; j < reticula[i].length; j++)
            {
                if(reticula[i][j] == Estados.Estable)
                    System.out.print("[ " + "Es" + " ]");
                else if(reticula[i][j] == Estados.Excitado)
                    System.out.print("[ " + "Ex" + " ]");
                else if(reticula[i][j] == Estados.Refractario)
                    System.out.print("[ " + "RE" + " ]");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public static void main(String[] args) throws InterruptedException
    {
        // Se considera Coeficiente de bloqueo igual a 0, debido a que es una tarea
        // puramente de calculo con condicion de frontera nula
        Scanner s = new Scanner(System.in);
        
        System.out.println("Introduzca la dimensión de la retícula => ");
        int n = s.nextInt();
        if(n <= 1)
        {
            System.err.println("N debe ser mayor que 1");
            return;
        }
        reticula = new Estados[n][n];
        
        System.out.println("Introduzca el número de tareas paralelas que desea => ");
        int m = s.nextInt();
        if(m > n || m < 0)
        {
            System.err.println("M debe ser <= N y > 0");
            return;
        }
        
        System.out.println("Introduzca el número de etapas de tiempo => ");
        int t = s.nextInt();
        
        System.out.println("¿Quiere usted carga los datos en la reticula de forma manual(1) o automática(2)? (1/2)");
        int f = s.nextInt();
        
        ThreadPoolExecutor ept;
        if(f == 1)
        {
            rellenarManual();
            System.out.println("Matriz inicial");
            System.out.println("==============");
            enseniarReticula();
        } else if(f == 2)
        {
            ept = new ThreadPoolExecutor(m, m, 1, TimeUnit.DAYS, new LinkedBlockingDeque<Runnable>());
            int min = 0;
            int ventana = n / m;
            int max = ventana;
            while(max <= n)
            {
                ept.execute(new Rellenar(reticula, min, max));
                min = max;
                max += ventana;
            }
            
            if(max > n)
            {
                max -= ventana;
                ept.execute(new Rellenar(reticula, max, n));
            }
            ept.shutdown();
            ept.awaitTermination(1, TimeUnit.DAYS);
        }
        
        long start = System.nanoTime();
        for (int i = 0; i < t; i++)
        {
            ept = new ThreadPoolExecutor(m, m, 1, TimeUnit.DAYS, new LinkedBlockingDeque<Runnable>());
            int min = 0;
            int ventana = n / m;
            int max = ventana;
            while(max + ventana <= n)
            {
                ept.execute(new Etapa(reticula, min, max));
                min = max;
                max += ventana;
            }
            
            if(max > n)
            {
                ept.execute(new Etapa(reticula, max, n));
            }
            ept.shutdown();
            ept.awaitTermination(1, TimeUnit.DAYS);
        }
        long end = System.nanoTime();
        
        if(f == 1)
        {
            System.out.println("Matriz final");
            System.out.println("============");
            enseniarReticula();
        }
        
        System.out.println("Ha tardado en calcular las etapas => " + (end - start) + " micro segundos.");
    }
}

class Rellenar implements Runnable
{
    private Estados[][] ret;
    private int min, max;
    
    public Rellenar(Estados[][] ret, int min, int max)
    {
        this.ret = ret;
        this.min = min;
        this.max = max;
    }
    
    @Override
    public void run()
    {
        Random r = new Random();
        for (int i = min; i < max; i++)
        {
            for (int j = 0; j < this.ret[i].length; j++)
            {
                int valor = r.nextInt(3) + 1;
                switch(valor)
                {
                    case 1: this.ret[i][j] = Estados.Estable; break;
                    case 2: this.ret[i][j] = Estados.Excitado; break;
                    case 3: this.ret[i][j] = Estados.Refractario; break;
                    default: this.ret[i][j] = Estados.Estable;
                }
            }
        }
    }
}

class Etapa implements Runnable
{
    private Estados[][] ret;
    private int min, max;
    
    public Etapa(Estados[][] ret, int min, int max)
    {
        this.ret = ret;
        this.min = min;
        this.max = max;
    }
    
    @Override
    public void run()
    {
        for (int i = this.min; i < this.max; i++)
        {
            for (int j = 0; j < this.ret[i].length; j++)
            {
                if(this.ret[i][j] == Estados.Excitado)
                {
                    this.ret[i][j] = Estados.Refractario;
                } else if(this.ret[i][j] == Estados.Refractario)
                {
                    this.ret[i][j] = Estados.Estable;
                } else if(this.ret[i][j] == Estados.Estable)
                {   
                    for (int k = i - 1; k < i + 2 && this.ret[i][j] == Estados.Estable; k++)
                    {
                        for (int l = j - 1 ; l < j + 2 && this.ret[i][j] == Estados.Estable; l++)
                        {
                            if(k < this.min || k >= this.max || j < 0 || j >= this.ret[i].length)
                                continue;
                            try
                            {
                                if(this.ret[k][l] == Estados.Excitado)
                                {
                                    this.ret[i][j] = Estados.Excitado;
                                }
                            } catch (Exception e) {}
                        }
                    }
                }
            }
        }
    }
}

enum Estados {
    Estable, Excitado, Refractario;
}