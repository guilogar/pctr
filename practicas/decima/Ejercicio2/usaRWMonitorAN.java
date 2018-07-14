import java.util.*;
import java.util.concurrent.*;

public class usaRWMonitorAN {
    public static void main (String[] args) throws InterruptedException {
        int N = 20;
        ExecutorService pool = Executors.newCachedThreadPool();
        RWMonitorAN rw = new RWMonitorAN(N);
        
        for (int i = 0; i < N; i++) {
            pool.execute(new Hilo(rw, i, i));
        }
        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.DAYS);
    }
}
