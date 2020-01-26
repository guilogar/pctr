/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.concurrent.*;

/**
 *
 * @author usuario
 */
public class F100 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        int maxArmamento = 4;
        int maxEstaciones = 16;
        CIWS armamento = new CIWS(maxArmamento);
        
        ExecutorService es = Executors.newFixedThreadPool(maxEstaciones);
        
        for (int i = 0; i < maxEstaciones; i++) {
            es.execute(new combatStations(armamento));
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.DAYS);
    }
    
}
