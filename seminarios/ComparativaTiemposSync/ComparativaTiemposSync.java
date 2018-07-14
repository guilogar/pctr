
public class ComparativaTiemposSync {
    public static void main (String[] args) throws InterruptedException {
        HiloSinBloqueo h1 = new HiloSinBloqueo();
        long inicio = System.currentTimeMillis();
        h1.start();
        h1.join();
        System.out.println("Tiempo sin bloqueo: " + (System.currentTimeMillis() - inicio) + " milisegundos.");
        
        HiloConBloqueo h2 = new HiloConBloqueo();
        long inicioh2 = System.currentTimeMillis();
        h2.start();
        h2.join();
        System.out.println("Tiempo con bloqueo: " + (System.currentTimeMillis() - inicioh2) + " milisegundos.");
    }
}

class HiloSinBloqueo extends Thread {
    static int contador;
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            ++contador;
        }
    }
}
class HiloConBloqueo extends Thread {
    static int contador;
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            synchronized(this) {
                ++contador;
            }
        }
    }
}
