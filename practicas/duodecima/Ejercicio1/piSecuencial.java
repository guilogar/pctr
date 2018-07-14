public class piSecuencial {
    private int puntos;

    public piSecuencial() {
        this.puntos = 1000;
    }

    public void masPuntos(int puntos) {
        this.puntos += puntos;
    }

    public void reset() {
        this.puntos = 0;
    }

    public double getPi() {
        double aciertos = 0;
        for (int i = 0; i < this.puntos; i++) {
            double x = Math.random() * 2 - 1;
            double y = Math.random() * 2 - 1;
            double z = x*x + y*y;

            if(z <= 1) {
                aciertos++;
            }
        }

        return (aciertos * 4) / this.puntos;
    }

    public static void main (String[] args) throws Exception {
        piSecuencial p = new piSecuencial();
        p.masPuntos(100000);
        System.out.println(p.getPi());
    }
}
