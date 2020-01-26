/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class pruebaMonitor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        
        Proceso[] p = new Proceso[10];
        for (int i = 0; i < p.length; i++) {
            p[i] = new Proceso(false);
            p[i].start();
        }
        
        for (int i = 0; i < p.length; i++) {
            p[i].join();
        }
        
        System.out.println("El valor sin control de exclusion mutua es => " + Proceso.variable);
        Proceso.variable = 0;
        
        p = new Proceso[10];
        for (int i = 0; i < p.length; i++) {
            p[i] = new Proceso(true);
            p[i].start();
        }
        
        for (int i = 0; i < p.length; i++) {
            p[i].join();
        }
        
        System.out.println("El valor con control de exclusion mutua es => " + Proceso.variable);
    }
    
}

class Proceso extends Thread
{
    public static int variable = 0;
    private boolean controlSeccionCritica;
    private monitorSem ms;
    
    public Proceso(boolean controlSeccionCritica)
    {
        this.controlSeccionCritica = controlSeccionCritica;
        this.ms = new monitorSem(1);
    }
    
    @Override
    public void run()
    {
        if(this.controlSeccionCritica)
        {
            for (int i = 0; i < 10; i++) {
                try {
                    this.ms.waitSem();
                    variable++;
                    this.ms.signalSem();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Proceso.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else
        {
            for (int i = 0; i < 10; i++) {
                variable++;
            }
        }
    }
}
