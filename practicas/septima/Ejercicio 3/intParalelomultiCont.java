import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class intParalelomultiCont implements Runnable
{
    private int intentos = 0;
    private int puntos = 0;
    
    public intParalelomultiCont(int intentos)
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
            
            if(Math.pow(cx, 2) + Math.pow(cy, 2) <= 1) this.puntos++;
        }
    }

    public int puntos() { return this.puntos; }
    
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
        
        ArrayList<intParalelomultiCont> hebras = new ArrayList<>();
        
        double initTiempo = System.currentTimeMillis();
        for (int i = 0; i < nTareas - 1 && lsup <= nIntentos; i++)
        {
            intParalelomultiCont ipmc = new intParalelomultiCont(lsup - linf);
            hebras.add(ipmc);
            ept.execute(ipmc);
            linf = lsup;
            lsup += lsup;
        }
        
        if(lsup > nIntentos)
        {
            intParalelomultiCont ipmc = new intParalelomultiCont(nIntentos - lsup);
            hebras.add(ipmc);
            ept.execute(ipmc);
        }
        
        ept.shutdown();
        ept.awaitTermination(1L, TimeUnit.DAYS);
        while(!ept.isTerminated());
        
        double tiempoTotal = System.currentTimeMillis() - initTiempo;
        utilsFile.writeInFile("info", "intparalelamulticont.txt", "" + nIntentos + " " + tiempoTotal + "\n");
        
        double puntos = 0.0;
        for (int i = 0; i < hebras.size(); i++)
            puntos += hebras.get(i).puntos();
        
        System.out.println("Valor de la integral => " + (puntos / nIntentos));
    }
}
