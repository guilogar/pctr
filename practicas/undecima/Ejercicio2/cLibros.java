import java.rmi.Naming;

public class cLibros {
    
    public static void main (String[] args) throws Exception {
        
        iLibros biblioteca = (iLibros) Naming.lookup("//localhost/Biblioteca");
        
        biblioteca.insertarLibro(new Libro("233 Metodos de Calculo", "233MC", 666));
        biblioteca.insertarLibro(new Libro("Ejercicios de Examen de PCTR", "EEPCTR", 666));
        biblioteca.insertarLibro(new Libro("Ejercicios de Examen de AAED", "EEAAED", 666));
        
        if(biblioteca.eliminarLibro(1) != null)
            System.out.println("Se ha eliminado con éxito.");
        
        if(biblioteca.eliminarLibro(new Libro("Ejercicios de Examen de AAED", "EEAAED", 666)))
            System.out.println("Se ha eliminado con éxito.");
        
        System.out.println("Hay en total " + biblioteca.numerosLibrosBiblioteca() + " libros disponibles en la biblioteca.");
    }
}
