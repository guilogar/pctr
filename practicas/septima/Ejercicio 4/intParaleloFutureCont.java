import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class intParaleloFutureCont implements Callable<Object>
{
    private int intentos = 0;
    private int puntos = 0;
    
    public intParaleloFutureCont(int intentos)
    {
        this.intentos = intentos;
    }
    
    @Override
    public Object call()
    {
        Random r = new Random();
        for(int i = 0; i < this.intentos; i++)
        {
            double cx = r.nextDouble();
            double cy = r.nextDouble();
            
            if(Math.pow(cx, 2) + Math.pow(cy, 2) <= 1) this.puntos++;
        }
        return new Integer(this.puntos);
    }
    
    public static void main (String[] args) throws Exception
    {
        double Cb = 1.0; // Coeficiente de bloqueo
        int nTareas = (int) Math.ceil(Runtime.getRuntime().availableProcessors() / Cb); // Ecuacion de subramanian
        int nIntentos = 1;
        try
        {
            nIntentos = Integer.parseInt(args[0]);
        } catch (Exception e) {}
        
        ThreadPoolExecutor ept = new ThreadPoolExecutor(
            nTareas, nTareas, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()
        );
        
        int linf = 0;
        int lsup = nIntentos / nTareas;
        
        ArrayList<Future<Object>> hebras = new ArrayList<Future<Object>>();
        
        double initTiempo = System.currentTimeMillis();
        for (int i = 0; i < nTareas - 1 && lsup <= nIntentos; i++)
        {
            hebras.add(ept.submit(new intParaleloFutureCont(lsup - linf)));
            linf = lsup;
            lsup += lsup;
        }
        
        if(lsup > nIntentos)
        {
            hebras.add(ept.submit(new intParaleloFutureCont(nIntentos - linf)));
        }
        
        ept.shutdown();
        ept.awaitTermination(1L, TimeUnit.DAYS);
        while(!ept.isTerminated());
        
        double tiempoTotal = System.currentTimeMillis() - initTiempo;
        utilsFile.writeInFile("info", "intparalelafuturecont.txt", "" + nIntentos + " " + tiempoTotal + "\n");
        
        double puntos = 0.0;
        for(Future<Object> iterador : hebras) {
            try{
                puntos += (int) iterador.get();
            } catch (CancellationException e) {}
              catch (ExecutionException e) {}
              catch (InterruptedException e) {}
        }
        
        System.out.println("Valor de la integral => " + (4.0 * (puntos / nIntentos)));
    }
}
