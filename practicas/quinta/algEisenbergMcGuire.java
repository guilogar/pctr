import java.util.*;
import java.lang.*;

public class algEisenbergMcGuire implements Runnable {
    public static final int N = 10;
    public static int numProcesos = 1;
    public volatile static int i = 0, j = 0;
    public volatile static Estados banderas[] = new Estados[N];
    public volatile static int turno = 0;
    
    private int indice;
    
    public algEisenbergMcGuire(int indice) {
        this.indice = indice;
    }
    
    public void run() {
        while(true) {
            do {
                banderas[i] = Estados.WAITING;
                j = turno;
                //System.out.println("i => " + i + ", j => " + j);
                while(j != i) {
                    if(banderas[j] != Estados.IDLE) {
                        j = turno;
                        i = (i + 1) % N;
                    } else {
                        j = (j + 1) % N;
                    }
                }
                
                banderas[i] = Estados.ACTIVE;
                j = 0;
                while((j < N-1) && ((j == i) || banderas[j] != Estados.ACTIVE))
                    j++;
                //System.out.println("i => " + i + ", j => " + j);
            } while(j >= N && (turno == i || banderas[turno] == Estados.IDLE));
            
            numProcesos++;
            numProcesos--;
            System.out.println("¿La concurrencia funciona? (numProcesos) => " + 
                                numProcesos + ", hilo("+this.indice+")");
            
            j = ++turno % N;
            while(banderas[j] == Estados.IDLE)
                j = (j + 1) % N;
            
            turno = j;
            banderas[i] = Estados.IDLE;
        }
    }
    
    public static void main (String[] args) {
        for (int i = 0; i < N; i++)
            new Thread(new algEisenbergMcGuire(i)).start();
    }
}
