import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Filosofos {
    
    public static void main (String[] args) {
        int numF = 5;
        Mesa m = new Mesa(numF);
        
        for (int i = 0; i < numF; i++)
        {
            new Filosofo(i, m).start();
        }
    }
}

class Mesa {
    
    private int numT;
    private ReentrantLock l = new ReentrantLock();
    private Condition[] condiciones;
    
    Mesa(int numTenedores)
    {
        this.numT = numTenedores;
        this.condiciones = new Condition[numTenedores];
        
        for (int i = 0; i < numTenedores; i++)
            this.condiciones[i] = this.l.newCondition();
    }
    
    public void comer(int i)
    {
        
    }
    
    public void pensar(int i)
    {
        
    }
}

class Filosofo extends Thread {
    private int id;
    private Mesa monitor;
    
    Filosofo(int id, Mesa monitor)
    {
        this.id = id;
        this.monitor = monitor;
    }
    
    @Override
    public void run()
    {
        this.monitor.comer(this.id);
        System.out.println("Comiendo: " + this.id);
        this.monitor.pensar(this.id);
        System.out.println("Pensar: " + this.id);
    }
}
