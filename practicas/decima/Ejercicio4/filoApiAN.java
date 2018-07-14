import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class filoApiAN {
    private int[] tenedores;
    private Condition[] arrayCondiciones;
    private ReentrantLock l;
    private int N;
    
    public filoApiAN(int n) {
        this.arrayCondiciones = new Condition[n];
        this.tenedores = new int[n];
        this.l = new ReentrantLock();
        this.N = n;
        
        for (int i = 0; i < this.tenedores.length; i++) {
            this.tenedores[i] = 1;
        }
        for (int i = 0; i < this.arrayCondiciones.length; i++) {
            this.arrayCondiciones[i] = l.newCondition();
        }
    }
    
    public void cojerTenedor(int id) {
        this.l.lock();
        while(this.tenedores[id] == 0 || this.tenedores[(id+1) % N] == 0) {
            try {
                this.arrayCondiciones[id].await();
            } catch(Exception e) {}
        }
        this.tenedores[id] -= 1;
        this.tenedores[(id+1) % N] -= 1;
        this.l.unlock();
    }
    
    public void dejarTenedor(int id) {
        this.l.lock();
        this.tenedores[id] += 1;
        this.tenedores[(id+1) % N] += 1;
        
        for (int i = 0; i < this.arrayCondiciones.length; i++) {
            this.arrayCondiciones[i].signalAll();
        }
        this.l.unlock();
    }
}
