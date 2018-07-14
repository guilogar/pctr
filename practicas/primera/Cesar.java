import java.lang.*;
import java.util.*;

public class Cesar {
    
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String fraseAux = "";
        
        System.out.print("Introduzca la frase que desea usted cifrar => ");
        String frase = sc.nextLine();
        
        System.out.print("Instroduzca ahora la constante de cifrado (Número Natural) => ");
        int n = sc.nextInt();
        
        for (int i = 0; i < frase.length(); ++i) {
            int newCharacter = (int) frase.charAt(i) + (n % 27);
            fraseAux += (char) newCharacter;
        }
        System.out.println("La frase cifrada es => " + fraseAux);
    }
}

