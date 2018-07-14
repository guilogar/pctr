import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import java.util.*;

public class eCalorParalela {
    private static int minTemp = -100;
    private static int maxTemp = 100;
    private static int N = 10;
    private static int tamNucleo = 2;
    
    public static void asignacionAutomatica() throws InterruptedException {
        double[][] array = new double[N][N];
        
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = 0;
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length && (i <= 0 || i == array.length - 1); j++) {
                array[i][j] = 100;
            }
            for (int j = (array[i].length / 2) - (tamNucleo / 2); (j < (array[i].length / 2) + (tamNucleo / 2))
                                                                  && (i >= (array.length / 2) - (tamNucleo/2))
                                                                  && (i <= (array.length / 2) + tamNucleo/2); j++) {
                array[i][j] = 50;
            }
            array[i][0] = 100;
            array[i][array[i].length - 1] = 100;
        }
        
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(array[i][j] != 0) {
                    if(array[i][j] == 100)
                        System.out.print("X");
                    if(array[i][j] == 50)
                        System.out.print("Y");
                    continue;
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        
        int nucleos = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(nucleos);
        
        Monitor m = new Monitor(array, 5, 3);
        int inicioRango = 1;
        int finRango = inicioRango + (array.length / nucleos);
        
        for (int i = 0; i < nucleos && inicioRango < array.length && finRango < array.length; i++) {
            pool.execute(new RellenaArray(m, inicioRango, finRango));
            
            inicioRango = finRango + 1;
            finRango = inicioRango + (array.length / nucleos);
        }
        
        if(inicioRango < array.length && finRango >= array.length) {
            pool.execute(new RellenaArray(m, inicioRango, array.length - 1));
        }
        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.DAYS);
        
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(" |" + array[i][j] + "| ");
            }
            System.out.println();
        }
    }
    
    public static void asignacionManual() {
    
    }
    
    public static void main (String[] args) throws Exception {
        System.out.println("¿Desea usted asignacion automatica de calor o asignarla usted mismo?");
        int opcion = 4;
        
        do {
            System.out.println("1) Asignación automatica.");
            System.out.println("2) Asignación manual.");
            System.out.println("3) Salir");
            System.out.println("=========================");
            System.out.print("Elija: ");
            Scanner s = new Scanner(System.in);
            opcion = s.nextInt();
            
            switch (opcion) {
                case 1: asignacionAutomatica(); break;
                case 2: asignacionManual(); break;
                case 3:
                    break;
                default: System.out.println("Error, opcion incorrecta. Escoja otra.");
            }
        } while(opcion >= 4);
    }
}

class Monitor {
    private ReentrantLock l;
    private Condition c;
    private double[][] array;
    private double coeficienteX;
    private double coeficienteY;
    
    public Monitor(double[][] array, double coeficienteX, double coeficienteY) {
        this.array = array;
        this.coeficienteX = coeficienteX;
        this.coeficienteY = coeficienteY;
        
        this.l = new ReentrantLock();
        this.c = l.newCondition();
    }
    
    public void rellenar(int inicioRango, int finRango) {
        l.lock();
        
        try {
            System.out.println(this.array[1][inicioRango-1]);
            System.out.println(this.array[1][finRango+1]);
            while(this.array[1][inicioRango-1] == 0 && this.array[1][finRango+1] == 0) {
                System.out.println("Se duerme hilo " + Thread.currentThread().getName());
                c.await();
            }
            
            for (int i = 1; i < this.array.length - 1; i++) {
                for(int j = inicioRango; j <= finRango; j++) {
                    this.array[i][j] = this.array[i][j] +
                                       this.coeficienteY * (this.array[i][j+1] + this.array[i][j-1] - 2*this.array[i][j]) +
                                       this.coeficienteX * (this.array[i+1][j] + this.array[i-1][j] - 2*this.array[i][j]);
                    //System.out.print(this.array[i][j]);
                }
                //System.out.println();
            }
            c.signal();
        } catch(Exception e) { }
        
        l.unlock();
    }
}

class RellenaArray implements Runnable {
    private int inicioRango;
    private int finRango;
    private Monitor m;
    
    public RellenaArray(Monitor m, int inicioRango, int finRango) {
        this.inicioRango = inicioRango;
        this.finRango = finRango;
        
        this.m = m;
    }
    
    public void run() {
        m.rellenar(this.inicioRango, this.finRango);
    }
}
