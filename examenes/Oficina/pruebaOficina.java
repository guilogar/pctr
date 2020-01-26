/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class pruebaOficina {
    
    public static void main(String[] args)
    {
        Oficina f = new Oficina(10);
        
        Empleado[] empleados = new Empleado[8];
        Limpiador[] limpiadores = new Limpiador[3];
        
        for (int i = 0; i < empleados.length; i++)
        {
            empleados[i] = new Empleado(f);
            new Thread(empleados[i]).start();
        }
        
        for (int i = 0; i < limpiadores.length; i++)
        {
            limpiadores[i] = new Limpiador(f);
            new Thread(limpiadores[i]).start();
        }
        
        
    }
}
