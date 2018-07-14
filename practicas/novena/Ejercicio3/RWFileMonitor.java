public class RWFileMonitor {
    volatile int readers = 0;
    volatile boolean writing = false;
    
    public synchronized void StartRead() {
        while (writing)
            try {
               wait();
            } catch (InterruptedException e) {}
        readers = readers + 1;
        System.out.println("Lector inicia lectura...");
        notifyAll();
    }
    
    public synchronized void EndRead() {
        readers = readers - 1;
        if (readers == 0) notifyAll();
        System.out.println("Lector finaliza lectura...");
    }
    
    public synchronized void StartWrite() {
        while (writing || (readers != 0))
            try {
               wait();
            } catch (InterruptedException e) {}
        writing = true;
        System.out.println("Escritor inicia escritura...");
    }
    
    public synchronized void EndWrite() {
        writing = false;
        notifyAll();
        System.out.println("Escritor finaliza escritura...");
    }
}
