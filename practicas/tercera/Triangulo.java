public class Triangulo extends Poligono {
    
    private double altura;
    
    public Triangulo(double altura) {
        super();
        this.altura = altura;
    }
    
    public Triangulo(Punto[] puntos, double altura) {
        super(puntos);
        this.altura = altura;
    }
    
    public double getArea() {
        return ((this.getPuntos()[0].getDistancia(this.getPuntos()[1]) * altura) / 2);
    }
    
    public String toString() {
        String info = "Area del triángulo => " + this.getArea() + " km^2.\n";
        return info + super.toString();
    }
}
