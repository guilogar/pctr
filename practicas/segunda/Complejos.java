import java.lang.*;
import java.util.*;

public class Complejos {
    private double[] c;
    
    public Complejos() {
        c = new double[2];
    }
    
    public Complejos(double real, double compleja) {
        c = new double[2];
        c[0] = real;
        c[1] = compleja;
    }
    
    public double getReal() {
        return this.c[0];
    }
    
    public double getCompleja() {
        return this.c[1];
    }
    
    public void setReal(double real) {
        this.c[0] = real;
    }
    
    public void setCompleja(double compleja) {
        this.c[1] = compleja;
    }
    
    public Complejos suma(Complejos comp) {
        return new Complejos(this.getReal() + comp.getReal(), this.getCompleja() + comp.getCompleja());
    }
    
    public Complejos resta(Complejos comp) {
        return new Complejos(this.getReal() - comp.getReal(), this.getCompleja() - comp.getCompleja());
    }
    
    public Complejos producto(Complejos comp) {
        double parteReal = (this.getReal() * comp.getReal()) - (this.getCompleja() * comp.getCompleja());
        double parteCompleja = (this.getReal() * comp.getCompleja()) + (this.getCompleja() * comp.getReal());
        
        return new Complejos(parteReal, parteCompleja);
    }
    
    public Complejos division(Complejos comp) {
        
        double comun = (Math.pow(comp.getReal(), 2) + Math.pow(comp.getCompleja(), 2));
        double parteReal = ((this.getReal() * comp.getReal()) + (this.getCompleja() * comp.getCompleja())) / comun;
        double parteCompleja = ((this.getCompleja() * comp.getReal()) - (this.getReal() * comp.getCompleja())) / comun;
        
        return new Complejos(parteReal, parteCompleja);
    }
    
    public String toString() {
        return this.c[0] + " + " + this.c[1] + "i";
    }
}
