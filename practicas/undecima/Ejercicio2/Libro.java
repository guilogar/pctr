import java.io.Serializable;

import java.util.*;

public class Libro implements Serializable {
    private double numPaginas;
    private String nombre;
    private String siglas;
    private static final long serialVersionUID = 1;
    
    public Libro(String nombre, String siglas, double numPaginas) throws Exception {
        this.nombre = nombre;
        this.numPaginas = numPaginas;
        this.siglas = siglas;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public double getNumPaginas() {
        return this.numPaginas;
    }
    
    public String getSiglas() {
        return this.siglas;
    }
    
    @Override
    public int hashCode() {
        return Integer.parseInt(this.nombre + this.siglas + this.numPaginas);
    }
    
    @Override
    public boolean equals(Object l) {
        Libro b = (Libro) l;
        return (this.numPaginas == b.getNumPaginas()        &&
                this.nombre.equalsIgnoreCase(b.getNombre()) &&
                this.siglas.equalsIgnoreCase(b.getSiglas()));
    }
}
