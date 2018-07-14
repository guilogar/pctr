public class Consumidor implements Runnable {
    private pcMonitor monitor = null;
    
    public Consumidor(pcMonitor monitor) {
        this.monitor = monitor;
    }
    
    public void run () {
        //System.out.println("arranca consumidor...");
        int item = this.monitor.take();
        System.out.println (" Consumiendo " + item );
        //System.out.println("termina consumidor...");
    }
}
