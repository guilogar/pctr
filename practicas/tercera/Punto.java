public class Punto {
    double x, y; // Variables de instancia
    
    public Punto(double x, double y) { // El constructor
        this.x= x; this.y= y;
    }
    
    public void moverEn(double dx, double dy) { // Un metodo
        this.x+= dx; this.y+= dy;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public int getCuadrante() {
        
        if(this.x < 0) {
            if(this.y < 0) {
                return 3;
            }
            return 2;
        } else if (this.x >= 0) {
            if(this.y < 0) {
                return 4;
            }
            return 1;
        }
        return 0;
    }
    
    public double getDistancia(int x, int y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }
    
    public double getDistancia(Punto punto) {
        double x = punto.getX();
        double y = punto.getY();
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }
    
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
