import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
    private int n1;
    private int n2;
    private ReentrantLock rl;
    private Condition c1;
    private Condition c2;
    private Condition c3;
    
    private int procesos1;
    private int procesos2;
    private int procesos3;
    
    public Monitor(int n1, int n2)
    {
        this.n1 = n1;
        this.n2 = n2;
        
        this.procesos1 = 0;
        this.procesos2 = 0;
        this.procesos3 = 0;
        
        this.rl = new ReentrantLock();
        this.c1 = this.rl.newCondition();
        this.c2 = this.rl.newCondition();
        this.c3 = this.rl.newCondition();
    }
    
    public synchronized int reservarr1(int n)
    {
        try {
            while(this.n1 - n < 0)
            {
                this.procesos1++;
                wait();
                this.procesos1--;
            }
        } catch(Exception e) {}
        return n;
    }
    
    public synchronized int reservarr2(int n)
    {
        try {
            while(this.n2 - n < 0)
            {
                this.procesos2++;
                wait();
                this.procesos2--;
            }
        } catch(Exception e) {}
        return n;
    }
    
    public synchronized int reservarr1r2(int n1, int n2)
    {
        try {
            while(this.n1 - n1 < 0 || this.n2 - n2 < 0)
            {
                this.procesos3++;
                wait();
                this.procesos3--;
            }
        } catch(Exception e) {}
        return n1 + n2;
    }
    
    public synchronized void liberarr1(int n)
    {
        try {
            this.n1 += n;
            if(this.procesos3 > 0)
                this.c3.signalAll();
            else if(this.procesos1 > 0)
                this.c1.signalAll();
        } catch (Exception e) {}
    }
    
    public synchronized void liberarr2(int n)
    {
        try {
            this.n2 += n;
            if(this.procesos3 > 0)
                this.c3.signalAll();
            else if(this.procesos2 > 0)
                this.c2.signalAll();
        } catch(Exception e) {}
    }
}
