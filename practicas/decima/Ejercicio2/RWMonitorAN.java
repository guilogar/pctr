import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import java.util.*;
import java.util.concurrent.*;

public class RWMonitorAN {
    volatile int readers = 0;
    volatile boolean writing = false;
    private Condition lectores;
    private Condition escritores;
    private ReentrantLock l;
    
    public RWMonitorAN(int n) {
        this.l = new ReentrantLock();
        lectores = l.newCondition();
        escritores = l.newCondition();
    }
    
    public void StartRead() {
        this.l.lock();
        while (writing)
            try {
               this.lectores.await();
            } catch (InterruptedException e) {}
        readers = readers + 1;
        System.out.println("Lector inicia lectura...");
        this.l.unlock();
    }
    
    public void EndRead() {
        this.l.lock();
        readers = readers - 1;
        if (readers == 0) {
            this.escritores.signalAll();
        }
        System.out.println("Lector finaliza lectura...");
        this.l.unlock();
    }
    
    public void StartWrite() {
        this.l.lock();
        while (writing || (readers != 0))
            try {
               this.escritores.await();
            } catch (InterruptedException e) {}
        writing = true;
        System.out.println("Escritor inicia escritura...");
        this.l.unlock();
    }
    
    public void EndWrite() {
        this.l.lock();
        writing = false;
        
        this.lectores.signalAll();
        this.escritores.signalAll();
        
        System.out.println("Escritor finaliza escritura...");
        this.l.unlock();
    }
}
