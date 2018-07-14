import java.lang.*;
import java.util.*;

public class Elipse {
    private int[] f1;
    private int[] f2;
    private double centro;
    
    public Elipse() {
        f1 = new int[2];
        f2 = new int[2];
    }
    
    public Elipse(int[] f1, int[] f2) {
        this.f1 = f1;
        this.f2 = f2;
        this.centro = (double) (f2[0] + f1[0]) / 2;
    }
    
    public void setF1(int[] f1) {
        this.f1 = f1;
    }
    
    public void setF2(int[] f2) {
        this.f2 = f2;
    }
    
    public int[] getF1() {
        return this.f1;
    }
    
    public int[] getF2() {
        return this.f2;
    }
    
    public boolean puntoAdentro(int[] punto) {
        
        double ejeX = Math.pow((punto[0] - centro), 2) / Math.pow(this.f1[0] - centro, 2);
        double ejeY = Math.pow((punto[0] - 0), 2) / Math.pow(this.f2[0] - centro, 2);
        
        return (ejeX + ejeY == 1);
    }
}
