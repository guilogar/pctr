/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingQueue;
/**
 *
 * @author usuario
 */

// La condicion de frontera escogida es que los laterales siempre estaran Vacios.
public class wireWorld {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        int n = 0;
        int d = 0;
        int m = 0;
        int Cb = 0;
        
        
        Scanner c = new Scanner(System.in);
        System.out.println("Digame el numero de generaciones que desea => ");
        n = c.nextInt();
        System.out.println("Digame la dimension (cuadrada) de la reticula => ");
        d = c.nextInt();
        System.out.println("Digame el numero de tareas paralelas que desea => ");
        m = c.nextInt();
        
        int cores = m / (1 - Cb);
        Reticula r = new Reticula(d);
        System.out.println("¿Desea rellenar manualmente la reticula o que se haga de forma aleatoria?");
        System.out.println("1º) Manual.");
        System.out.println("2º) Aleatoria");
        System.out.println("Elija => ");
        
        switch(c.nextInt()) {
            case 1: r.rellenarManual(); break;
            case 2: {
                ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                        cores, cores, 1, TimeUnit.DAYS, new LinkedBlockingQueue<> ()
                );
                int liminf = 0;
                int ventana = d / cores;
                int limsup = ventana;
                
                while(limsup < d)
                {
                    tpe.execute(new RellenarReticula(r, liminf, limsup));
                    liminf = limsup;
                    limsup += ventana;
                }
                
                tpe.execute(new RellenarReticula(r, liminf, d - liminf));
                tpe.shutdown();
                tpe.awaitTermination(1, TimeUnit.DAYS);
            }; break;
        }
        
        System.out.println("Estado inicial de la reticula.");
        r.enseniarGeneracion();
        
        System.out.println("Empiezan las generaciones.");
        for (int i = 0; i < n; i++) {
            System.out.println((i+1) + "º) Generacion.");
            ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                    cores, cores, 1, TimeUnit.DAYS, new LinkedBlockingQueue<Runnable> ()
            );
            
            int liminf = 0;
            int ventana = d / cores;
            int limsup = ventana;
            
            while(limsup < d)
            {
                tpe.execute(new Generacion(r, liminf, limsup));
                liminf = limsup;
                limsup += ventana;
            }

            tpe.execute(new Generacion(r, liminf, d - liminf));
            tpe.shutdown();
            tpe.awaitTermination(1, TimeUnit.DAYS);
            
            r.enseniarGeneracion();
        }
    }
    
}

class Generacion implements Runnable {
    
    private int inicio, fin;
    private Reticula ret;
    
    public Generacion(Reticula ret, int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
        this.ret = ret;
    }
    
    public void run() {
        this.ret.crearGeneracion(this.inicio, this.fin);
    }
}

class RellenarReticula implements Runnable {
    
    private int inicio, fin;
    private Reticula ret;
    
    public RellenarReticula(Reticula ret, int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
        this.ret = ret;
    }
    
    public void run() {
        this.ret.rellenarAleatorio(this.inicio, this.fin);
    }
}

class Reticula {
    
    public static Estados[][] ret = null;
    
    public Reticula(int d) {
        this.ret = new Estados[d][d];
    }
    
    public void rellenarAleatorio(int inicio, int fin) {
        Random r = new Random();
        Estados[] rs = new Estados[4];
        rs[0] = Estados.V; rs[1] = Estados.FE;
        rs[2] = Estados.CE; rs[3] = Estados.C;
        
        for (int i = inicio; i < fin; i++) {
            for (int j = 0; j < this.ret[i].length; j++) {
                this.ret[i][j] = rs[r.nextInt(4)];
            }
        }
    }
    
    public void rellenarManual() {
        Scanner c = new Scanner(System.in);
        
        for (int i = 0; i < this.ret.length; i++) {
            for (int j = 0; j < this.ret[i].length; j++) {
                System.out.println("Elija el valor de la reticula para i = " + i +" y j = " + j);
                System.out.println("1 = Vacio");
                System.out.println("2 = Frente de Electron");
                System.out.println("3 = Cola de Electron");
                System.out.println("4 = Conductor");
                int t = c.nextInt();
                
                switch(t) {
                    case 1: this.ret[i][j] = Estados.V; break;
                    case 2: this.ret[i][j] = Estados.FE; break;
                    case 3: this.ret[i][j] = Estados.CE; break;
                    case 4: this.ret[i][j] = Estados.C; break;
                    default: this.ret[i][j] = Estados.V; break;
                }
            }
        }
    }
    
    public void crearGeneracion(int inicio, int fin) {
        for (int i = inicio; i < fin; i++) {
            for (int j = 0; j < this.ret[i].length; j++) {
                if(this.ret[i][j] == Estados.V)
                    this.ret[i][j] = Estados.V;
                else if(this.ret[i][j] == Estados.FE)
                    this.ret[i][j] = Estados.CE;
                else if(this.ret[i][j] == Estados.CE)
                    this.ret[i][j] = Estados.C;
                else if(this.ret[i][j] == Estados.C)
                {
                    Estados arriba = (i == inicio) ? Estados.V : this.ret[i - 1][j];
                    Estados abajo = (i == fin - 1) ? Estados.V : this.ret[i + 1][j];
                    Estados izq = (j == 0) ? Estados.V : this.ret[i][j-1];
                    Estados der = (j == this.ret[i].length - 1) ? Estados.V : this.ret[i][j+1];
                    
                    int totalVecinosFE = 0;
                    
                    if(arriba == Estados.FE) totalVecinosFE++;
                    if(abajo == Estados.FE) totalVecinosFE++;
                    if(izq == Estados.FE) totalVecinosFE++;
                    if(der == Estados.FE) totalVecinosFE++;
                    
                    if(totalVecinosFE > 0 && totalVecinosFE <= 2)
                        this.ret[i][j] = Estados.FE;
                }
            }
        }
    }
    
    public void enseniarGeneracion() {
        for (int i = 0; i < this.ret.length; i++) {
            for (int j = 0; j < this.ret[i].length; j++) {
                Estados t = this.ret[i][j];
                
                if(t == Estados.C)
                {
                    System.out.print("[C ]");
                } else if(t == Estados.FE)
                {
                    System.out.print("[FE]");
                } else if(t == Estados.CE)
                {
                    System.out.print("[CE]");
                } else
                {
                    System.out.print("[V ]");
                }
            }
            System.out.println("");
        }
    }
}

enum Estados {
    
    V, FE, CE, C;
 // Vacio, Frente de electron, Cola de electron, Conductor
}
