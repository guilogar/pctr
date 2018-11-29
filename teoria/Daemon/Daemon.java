package daemon;

public class Daemon extends Thread {
    
    @Override
    public void run() {
        System.out.println("Run del hilo daemon.");
        while (true) {
            // esperar evento
            // procesar evento
        }
    }
    
    public static void main (String[] args) throws Exception {
        Daemon h1 = new Daemon();
        h1.setDaemon(true);
        h1.start();
        // h1.join();
    }
}
