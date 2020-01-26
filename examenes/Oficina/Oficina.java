/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class Oficina {
    
    private boolean[] mesasLimpias;
    
    public Oficina(int n)
    {
        this.mesasLimpias = new boolean[n];
    }
    
    private int algunaMesaLimpia()
    {
        for(int i = 0; i < this.mesasLimpias.length; i++)
        {
            if(this.mesasLimpias[i])
                return i;
        }
        return -1;
    }
    
    private int algunaMesaSucia()
    {
        for(int i = 0; i < this.mesasLimpias.length; i++)
        {
            if(!this.mesasLimpias[i])
                return i;
        }
        return -1;
    }
    
    public synchronized void empleadoMesa() throws InterruptedException
    {
        while(this.algunaMesaLimpia() == -1)
        {
            notifyAll();
            wait();
        }
        int p = this.algunaMesaLimpia();
        System.out.println("La la la la un empleado de Google siendo feliz de trabajar en la mesa " + p);
        
        this.mesasLimpias[p] = false;
        notifyAll();
    }
    
    public synchronized void limpiadorMesa() throws InterruptedException
    {
        while(this.algunaMesaSucia() == -1)
        {
            notifyAll();
            wait();
        }
        int p = this.algunaMesaSucia();
        System.out.println("La la la la un limpiador de Google siendo feliz de limpiar la mesa " + p);
        
        this.mesasLimpias[p] = true;
        notifyAll();
    }
}
