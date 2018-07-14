public class Poligono {
    //Atributos
    private Punto[] puntos;
    private double perimetro = 0;
    private double area = 0;
    
    public Poligono() {
        puntos = new Punto[100];
    }
    
    public Poligono(Punto[] puntos) {
        puntos = new Punto[puntos.length];
        for (int i = 0; i < puntos.length; ++i) {
            this.puntos[i] = puntos[i];
        }
    }
    
    public void moverPoligono(int x, int y) {
        for (int i = 0; i < this.puntos.length; ++i) {
            this.puntos[i].moverEn(x, y);
        }
    }
    
    public double getPerimetro() {
        for (int i = 0; i < this.puntos.length - 1; ++i) {
            this.perimetro += this.puntos[i].getDistancia(this.puntos[i+1]);
        }
        return this.perimetro;
    }
    
    public Punto[] getPuntos() {
        return this.puntos;
    }
    
    public String toString() {
        String pol = "Puntos del Poligono => ";
        for (int i = 0; i < this.puntos.length; ++i) {
            pol += this.puntos[i].toString();
        }
        pol += ".";
        return pol;
    }
    
    public double getArea() {
        return this.area;
    }
}
