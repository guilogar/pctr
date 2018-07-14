import java.util.concurrent.CyclicBarrier;

class ComprobarGanador implements Runnable{
    Corredor[] corredores;
    public ComprobarGanador(Corredor[] corredores) {
        this.corredores = corredores;
    }
    
    public void run() {
        double mejorTiempoNatacion = this.corredores[0].getTiempoNatacion();
        int idCorredorNatacion = this.corredores[0].getId();
        for (int i = 1; i < this.corredores.length; i++) {
            if(this.corredores[i].getTiempoNatacion() < mejorTiempoNatacion) {
                mejorTiempoNatacion = this.corredores[i].getTiempoNatacion();
                idCorredorNatacion = this.corredores[i].getId();
            }
        }
        System.out.println("El mejor tiempo en natacion es del corredor " + 
                           idCorredorNatacion + " con un tiempo de " + 
                           mejorTiempoNatacion + "segundos.");
        
        double mejorTiempoCiclista = this.corredores[0].getTiempoCiclista();
        int idCorredorCiclista = this.corredores[0].getId();
        for (int i = 1; i < this.corredores.length; i++) {
            if(this.corredores[i].getTiempoCiclista() < mejorTiempoCiclista) {
                mejorTiempoCiclista = this.corredores[i].getTiempoCiclista();
                idCorredorCiclista = this.corredores[i].getId();
            }
        }
        System.out.println("El mejor tiempo en cilismo es del corredor " + 
                           idCorredorCiclista + " con un tiempo de " + 
                           mejorTiempoCiclista + "segundos.");
        
        double mejorTiempoPie = this.corredores[0].getTiempoPie();
        int idCorredorPie = this.corredores[0].getId();
        for (int i = 1; i < this.corredores.length; i++) {
            if(this.corredores[i].getTiempoPie() < mejorTiempoPie) {
                mejorTiempoPie = this.corredores[i].getTiempoPie();
                idCorredorPie = this.corredores[i].getId();
            }
        }
        System.out.println("El mejor tiempo en pie es del corredor " + 
                           idCorredorNatacion + " con un tiempo de " + 
                           mejorTiempoNatacion + "segundos.");
    }
}

public class triatlonBarreras {
    private static final int N = 100;
    private static final int NBarrier = 3;
    
    public static void main (String[] args) {
        
        Corredor[] corredores = new Corredor[N];
        Thread[] hilos = new Thread[N];
        CyclicBarrier[] barreras = new CyclicBarrier[NBarrier];
        
        for(int i = 0; i < barreras.length - 1; i++) {
            barreras[i] = new CyclicBarrier(N);
        }
        barreras[barreras.length - 1] = new CyclicBarrier(N, new ComprobarGanador(corredores));
        for (int i = 0; i < N; i++) {
            corredores[i] = new Corredor(barreras, i);
            hilos[i] = new Thread(corredores[i]);
            hilos[i].start();
        }
        
        
    }
}
