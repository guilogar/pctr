import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    
    public static void main (String[] args) {
        
        ExecutorService es = Executors.newCachedThreadPool();
        
        for (int i = 0; i < 500; i++)
        {
            new Thread(new Peaton()).start();
        }
    }
}

class Peaton implements Runnable {
    private static int pasarela = 0;
    private static Object cerrojo = new Object();
    
    public Peaton() {}
    
    @Override
    public void run() {
        synchronized(cerrojo)
        {
            pasarela++;
            System.out.println("Actualmente en la pasarela => " + pasarela);
            pasarela--;
        }
    }
}
