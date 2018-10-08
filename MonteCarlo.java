import java.util.*;

public class MonteCarlo {
    
    public double f(double x) {
        return x*x / 2;
        //return x;
    }
    
    public double integral(int n) {
        
        long exitos = 0;
        Random r = new Random();
        
        for (int i = 0; i < n; i++) {
            double y = r.nextDouble();
            double x = r.nextDouble();
            /*
             *double y = Math.random();
             *double x = Math.random();
             */
            
            if(y <= this.f(x)) exitos++;
        }
        
        return ((double) exitos / n);
    }
    
    public static void main (String[] args) {
        
        MonteCarlo mc = new MonteCarlo();
        double res = mc.integral(10000000);
        System.out.println(res);
    }
}
