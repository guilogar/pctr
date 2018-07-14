import java.util.*;

public class MonteCarloParalelo implements Runnable {
    int totalPuntos, dentro = 0;
    
    public MonteCarloParalelo(int totalPuntos) {
        this.totalPuntos = totalPuntos;
    }
    
    public void run() {
        for (int i = 0; i < totalPuntos; ++i) {
            Random r = new Random();
            double x = r.nextDouble(), y = r.nextDouble();
            if(Math.sin(x) >= y) {
                ++dentro;
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        int total = Integer.parseInt(args[0]);
        
        Thread[] hilos = new Thread[Integer.parseInt(args[1])];
        MonteCarloParalelo[] tareas = new MonteCarloParalelo[hilos.length];
        
        for (int i = 0; i < hilos.length; ++i) {
            tareas[i] = new MonteCarloParalelo(total / hilos.length);
            hilos[i] = new Thread(tareas[i]);
            hilos[i].start();
        }
        int totalDentro = 0;
        for (int i = 0; i < hilos.length; ++i) {
            hilos[i].join();
            totalDentro += tareas[i].dentro;
        }
        System.out.println("Dentro: ");
    }
}
