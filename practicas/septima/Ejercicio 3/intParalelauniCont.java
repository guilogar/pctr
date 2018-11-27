import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class intParalelauniCont implements Runnable
{
    public static int puntos = 0;
    public static Object cerrojo = new Object();
    
    private int intentos = 0;
    
    public intParalelauniCont(int intentos)
    {
        this.intentos = intentos;
    }
    
    @Override
    public void run()
    {
        Random r = new Random();
        for(int i = 0; i < this.intentos; i++)
        {
            double cx = r.nextDouble();
            double cy = r.nextDouble();
            
            if(Math.pow(cx, 2) + Math.pow(cy, 2) <= 1)
            {
                synchronized(cerrojo)
                {
                    puntos++;
                }
            }
        }
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
        
        double initTiempo = System.currentTimeMillis();
        for (int i = 0; i < nTareas - 1 && lsup <= nIntentos; i++)
        {
            ept.execute(new intParalelauniCont(lsup - linf));
            linf = lsup;
            lsup += lsup;
        }
        
        if(lsup > nIntentos)
            ept.execute(new intParalelauniCont(nIntentos - lsup));
        
        ept.shutdown();
        ept.awaitTermination(1L, TimeUnit.DAYS);
        
        double tiempoTotal = System.currentTimeMillis() - initTiempo;
        utilsFile.writeInFile("info", "intparalelaunicont.txt", "" + nIntentos + " " + tiempoTotal + "\n");
        System.out.println("Valor de la integral => " + ((double) puntos / nIntentos));
    }
}
