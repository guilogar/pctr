/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author usuario
 */
public class perfectNumbers {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // TODO code application logic here
        int n = 0;
        int tareas = 0;
        try {
            n = Integer.parseInt(args[0]);
            tareas = Integer.parseInt(args[1]);
        } catch(Exception e) {
            System.out.println("Los parametros N o tareas faltan.");
            System.out.println("Se toma N = 10000");
            System.out.println("Se toma tareas = 8");
            n = 1000;
            tareas = 8;
            //return;
        }
        int liminf = 0;
        int ventana = n/tareas;
        int limsup = ventana;
        
        ExecutorService es = Executors.newFixedThreadPool(tareas);
        ArrayList<Future<Long>> tareasAsincronas = new ArrayList<>();
        
        long start = System.currentTimeMillis();
        while(limsup < n)
        {
            tareasAsincronas.add(es.submit(new NumeroPerfecto(liminf, limsup)));
            liminf = limsup;
            limsup += ventana;
        }
        es.submit(new NumeroPerfecto(liminf, n - liminf));
        es.shutdown();
        es.awaitTermination(1, TimeUnit.DAYS);
        
        long totalNP = 0;
        for(Future<Long> it : tareasAsincronas)
        {
            totalNP += it.get();
        }
        long end = System.currentTimeMillis();
        
        long time = end - start;
        
        System.out.println("Numeros Perfectos encontrados: " + totalNP + ", con N = " + n + " y tareas = " + tareas + ".");
        System.out.println("Han sido encontrados en: " + time + " milisegundos.");
    }
    
}

class NumeroPerfecto implements Callable<Long> {
    private Long total;
    private int liminf;
    private int limsup;
    
    public NumeroPerfecto(int liminf, int limsup) {
        this.total = new Long(0);
        this.liminf = liminf;
        this.limsup = limsup;
    }
    
    public Long call() {
        for(int i = this.liminf; i < this.limsup; i++)
        {
            if(this.esNP(i)) this.total++;
        }
        return this.total;
    }
    
    private boolean esNP(int n) {
        ArrayList<Integer> divisores = new ArrayList<>();
        for(int i = 1; i < n; i++)
        {
            if(n % i == 0) divisores.add(i);
        }
        int suma = 0;
        for(Integer p : divisores)
        {
            suma += p;
        }
        
        return (suma == n);
    }
}
