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
public class combatStations implements Runnable {
    
    private CIWS armamento;
    
    public combatStations(CIWS monitor) {
        this.armamento = monitor;
    }
    
    @Override
    public void run() {
        int n = this.armamento.getMaxArmamento();
        for (int i = 0; i < n; i++) {
            try {
                this.armamento.reservarEstacion(i);
                System.out.println("Disparando con el armamento " + i + ".");
                this.armamento.liberarEstacion(i);
            } catch (InterruptedException ex) {
                Logger.getLogger(combatStations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
