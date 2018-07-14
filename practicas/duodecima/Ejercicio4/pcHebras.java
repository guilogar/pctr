import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class pcHebras {
    
    public static void main (String[] args) throws Exception {
        int N = 100;
        
        pcMonitor monitor = new pcMonitor(N);
        for (int i = 1000; i <= 10000; i++) {
            ExecutorService pool = Executors.newCachedThreadPool();
            long start = System.currentTimeMillis();
            for (int j = 0; j < i; j++) {
                pool.execute(new Consumidor(monitor));
                pool.execute(new Productor(monitor));
            }
            
            pool.shutdown();
            pool.awaitTermination(1L, TimeUnit.DAYS);
            long end = System.currentTimeMillis();
            long tiempo = end-start;
            
            utilsFile.writeInFile("info", "java_tiempo.txt", i + " " + tiempo + "\n");
            System.out.println("Tiempo con " + i + " consumidores y productores => " + tiempo);
        }
    }
}
