public class Filosofo implements Runnable {
    private int id;
    private filoApiAN m;
    
    public Filosofo(int id, filoApiAN m) {
        this.id = id;
        this.m = m;
    }
    
    public void run() {
        this.m.cojerTenedor(this.id);
        System.out.println("El filosofo " + this.id + " ha podido comer.");
        this.m.dejarTenedor(this.id);
    }
}
