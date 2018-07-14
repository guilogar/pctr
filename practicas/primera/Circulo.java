import java.util.*;

public class Circulo {
    private static final double valorPi = Math.PI;
    
    public static void main(String[] args) {
        double diametro = 14.2;
        double altura = 20;
        System.out.println("El volumen del cono es => " + (Math.pow(diametro/2, 2) * altura * valorPi) / 3 + " cm cubicos.");
    }
}
