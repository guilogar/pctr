import java.util.*;
import java.util.concurrent.*;

public class usaConductores {
    private static int N = 20;
    
    public static void main (String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        Conductores cs = new Conductores(N);
        
        for (int i = 0; i < N + 10; i++) {
            pool.execute(new Conductor(cs, i));
        }
        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.DAYS);
    }
}
