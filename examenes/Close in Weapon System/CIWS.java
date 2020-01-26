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
public class CIWS {
    
    private boolean[] armamentoOcupado;
    private Condition[] esperaArmamento;
    private ReentrantLock rl = new ReentrantLock();
    
    public CIWS(int n)
    {
        this.armamentoOcupado = new boolean[n];
        this.esperaArmamento = new Condition[n];
        
        for(int i = 0; i < n; i++) {
            this.armamentoOcupado[i] = false;
            this.esperaArmamento[i] = this.rl.newCondition();
        }
    }
    
    public synchronized void reservarEstacion(int i) throws InterruptedException {
        this.rl.lock();
        
        while(this.armamentoOcupado[i])
            this.esperaArmamento[i].await();
        
        this.armamentoOcupado[i] = true;
        
        this.rl.unlock();
    }
    
    public synchronized void liberarEstacion(int i) throws InterruptedException {
        this.rl.lock();
        
        this.armamentoOcupado[i] = false;
        this.esperaArmamento[i].signalAll();
        
        this.rl.unlock();
    }
    
    public int getMaxArmamento() {
        return this.armamentoOcupado.length;
    }
}
