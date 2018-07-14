import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class usaFiloApiAN {
    public static void main (String[] args) throws InterruptedException {
        int N = 4;
        ExecutorService pool = Executors.newCachedThreadPool();
        filoApiAN m = new filoApiAN(N);
        
        for (int i = 0; i < N; i++) {
            pool.execute(new Filosofo(i, m));
        }
        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.DAYS);
    }
}
