import java.util.*;
import java.util.concurrent.*;

public class usaMonitorCadena {
    
    public static void main (String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        Buffer f1 = new Buffer(30);
        Buffer f2 = new Buffer(30);
        
        for (int i = 0; i < 10; i++) {
            pool.execute(new ProcesoA(f1, 10));
        }
        
        for (int i = 0; i < 10; i++) {
            pool.execute(new ProcesoB(f1, f2, 10));
        }
        
        for (int i = 0; i < 10; i++) {
            pool.execute(new ProcesoC(f2));
        }
        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.DAYS);
    }
}
