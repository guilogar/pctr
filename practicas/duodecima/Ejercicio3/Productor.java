public class Productor implements Runnable {
    private pcMonitor monitor = null;
    
    public Productor (pcMonitor monitor) {
        this.monitor = monitor;
    }
    
    public void run () {
        //System.out.println("arranca productor...");
        int item = 0;
        this.monitor.put(++item);
        System.out.println (" Produciendo " + item );
        //System.out.println("termina productor...");
    }
}
