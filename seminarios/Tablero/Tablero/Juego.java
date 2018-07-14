import java.lang.*;
import java.util.*;

public class Juego {
    
    public static void main (String[] args) {
        Tablero t = new Tablero(5, 6);
        
        do {
            // Turno jugador 1
            t.imprimir();
            Scanner teclado = new Scanner(System.in);
            
            int columna;
            
            do {
                System.out.print("Jugador 1, elija una columna libre: ");
                columna = teclado.nextInt();
            } while(columna < 0 || columna >= t.tablero[0].length || 
                    !t.huecoLibre(columna));
            
            t.introducirFicha(columna, 'x');
            
            if(!t.ganador() && t.espacio()) {
                // Turno jugador 2
                t.imprimir();
                do {
                    System.out.print("Jugador 2, elija una columna libre: ");
                    columna = teclado.nextInt();
                } while(columna < 0 || columna >= t.tablero[0].length || 
                        !t.huecoLibre(columna));
                t.introducirFicha(columna, 'o');
            }
                
        } while ( !t.ganador() && t.espacio() );
    }
}
