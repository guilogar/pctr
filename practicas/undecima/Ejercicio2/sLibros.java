import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class sLibros extends UnicastRemoteObject implements iLibros {
    private static final long serialVersionUID = 1;
    private ArrayList<Libro> libros;
    
    public sLibros() throws RemoteException {
        this.libros = new ArrayList<Libro>();
    }
    
    public void insertarLibro(Libro l) {
        System.out.println(l.getNombre());
        this.libros.add(l);
    }
    
    public boolean eliminarLibro(Libro l) {
        return this.libros.remove(l);
    }
    
    public Libro eliminarLibro(int indice) {
        return this.libros.remove(indice);
    }
    
    public Libro consultarLibro(Libro l) {
        Libro b = null;
        Iterator<Libro> t = this.libros.iterator();
        
        while(t.hasNext() && b != null) {
            Libro c = t.next();
            if(l.equals(c)) {
                b = c;
            }
        }
        
        return b;
    }
    
    public Libro consultarLibro(String nombre) {
        Libro b = null;
        Iterator<Libro> t = this.libros.iterator();
        
        while(t.hasNext() && b != null) {
            Libro c = t.next();
            if(nombre.equalsIgnoreCase(c.getNombre())) {
                b = c;
            }
        }
        
        return b;
    }
    
    public Libro consultarLibro(String nombre, String siglas) {
        Libro b = null;
        Iterator<Libro> t = this.libros.iterator();
        
        while(t.hasNext() && b != null) {
            Libro c = t.next();
            if(nombre.equalsIgnoreCase(c.getNombre()) &&
               siglas.equalsIgnoreCase(c.getSiglas())) {
                b = c;
            }
        }
        
        return b;
    }
    
    public double numerosLibrosBiblioteca() {
        return this.libros.size();
    }
    
    public static void main (String[] args) throws Exception {
        iLibros l = new sLibros();
        Naming.bind("Biblioteca", l);
        System.out.println("Servidor de la Biblioteca preparado.");
    }
}
