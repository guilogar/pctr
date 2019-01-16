import java.util.Random;

public class Main {
    
    public static void main (String[] args) {
        Monitor m = new Monitor(5, 3);
        
        for (int i = 0; i < 25; i++)
        {
            new Hilo(m, new Random().nextInt(4)).start();
        }
    }
}
