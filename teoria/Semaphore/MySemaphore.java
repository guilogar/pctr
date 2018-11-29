import java.util.ArrayList;

public class MySemaphore {
    int S, bloqueados = 0;
    //ArrayList<Object> list;
    
    public MySemaphore(int s) {
        this.S = s;
    }
    
    public synchronized void acquire() throws Exception {
        
        if(this.S > 0)
        {
            --this.S;
        }
        else
        {
            ++this.bloqueados;
            wait();
        }
    }
    
    public synchronized void release() throws Exception {
        if(this.bloqueados > 0)
        {
            --this.bloqueados;
            notify();
        }
        else
        {
            ++this.S;
        }
    }
    
    public static void main (String[] args) {
        MySemaphore ms = new MySemaphore(2);
    }
}
