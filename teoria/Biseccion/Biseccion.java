
public class Biseccion {
    static double f2(double x) { return ( x * x ) - 5; }
    
    public static void main(String args[])
    {
        double a = 2, b = 3, c = 0, f_a, f_c, m = 0.1;
        
        while(b - a > m)
        {
            c = (a + b) / 2; f_a = f2(a); f_c = f2(c);
            
            if(f_a * f_c < 0) b = c;
            else { if(f_a * f_c > 0) a = c; else a=b=c; }
        }
        
        System.out.println(c + " es solucion");
    }
}
