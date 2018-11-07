import java.util.concurrent.*;
import java.util.*;

public class primosParalelos {
    public static void main(String[] args) throws Exception {
         long nPuntos     = Integer.parseInt(args[0]);
         int  nTareas     = Runtime.getRuntime().availableProcessors();
         long tVentana    = nPuntos/nTareas;
         long primosTotal = 0;
         long linf        = 0;
         long lsup        = tVentana;
         double tiempoUno = 0;
         
         for (int k = 1; k <= nTareas; k++) {
             primosTotal = 0;
             linf        = 0;
             lsup        = tVentana;
             
             ArrayList<Future<Object>> contParciales = new ArrayList<Future<Object>>();
             long inicTiempo = System.nanoTime();
             
             ThreadPoolExecutor ept;
             ept = new ThreadPoolExecutor(k, k, 0L, TimeUnit.MILLISECONDS, 
                                          new LinkedBlockingQueue<Runnable>());
             for(int i=0; i<nTareas; i++){
                 contParciales.add(ept.submit(new tareaPrimos(linf, lsup)));
                 linf = lsup+1;
                 lsup += tVentana;
             }
             
             for(Future<Object> iterador:contParciales) {
                 try{
                     primosTotal += (long)iterador.get();
                 } catch (CancellationException e) {}
                   catch (ExecutionException e) {}
                   catch (InterruptedException e) {}
             }
             
             long tiempoTotal = (System.nanoTime()-inicTiempo)/(long)1.0e9;
             ept.shutdown();
             
             if(k == 1) {
                tiempoUno = tiempoTotal;
             }
             System.out.println("Numeros Tareas: "+k);
             System.out.println("Primos hallados: "+primosTotal);
             System.out.println("Calculo finalizado en "+tiempoTotal+" segundos");
             
             utilsFile.writeInFile("info", "curva_cores.txt", ""+k+" "+tiempoTotal+"\n");
             utilsFile.writeInFile("info", "speed_up.txt", ""+k+" "+(tiempoUno / tiempoTotal)+"\n");
         }
    }
}
