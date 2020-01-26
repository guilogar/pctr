/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author usuario
 */
public class monitorSem {
    
    private ReentrantLock rl;
    private Condition c;
    private int hebras;
    
    public monitorSem(int hebras)
    {
        this.hebras = hebras;
        this.rl = new ReentrantLock();
        this.c = this.rl.newCondition();
    }
    
    public void waitSem() throws InterruptedException
    {
        this.rl.lock();
            while(hebras <= 0)
                this.c.await();
            hebras--;
        this.rl.unlock();
    }
    
    public void signalSem()
    {
        this.rl.lock();
        hebras++;
        this.c.signal();
        this.rl.unlock();
    }
}
