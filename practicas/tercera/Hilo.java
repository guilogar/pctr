public class Hilo extends Thread {
    
    private int tipoHilo;
    private static int n = 0;
    private int nVueltas;
    
    public Hilo(int nVueltas, int tipoHilo) {
        this.nVueltas=nVueltas; this.tipoHilo=tipoHilo;
    }
    
    public void run() {
        switch(tipoHilo){
          case 0: for(int i=0; i<nVueltas; i++)n++; break;
          case 1: for(int i=0; i<nVueltas; i++)n--; break;
        }
    }
    
    public static int getN() {
        return n;
    }
}
