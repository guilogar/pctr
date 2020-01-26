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
public class Limpiador implements Runnable {
    
    private Oficina f;
    
    public Limpiador(Oficina f)
    {
        this.f = f;
    }
    
    public void run()
    {
        while(true)
        {
            try {
                this.f.limpiadorMesa();
            } catch (InterruptedException ex) {
                Logger.getLogger(Limpiador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
