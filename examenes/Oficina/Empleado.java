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
public class Empleado implements Runnable {
    
    private Oficina f;
    
    public Empleado(Oficina f)
    {
        this.f = f;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try {
                this.f.empleadoMesa();
            } catch (InterruptedException ex) {
                Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
