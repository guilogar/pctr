import java.rmi.Naming;
import java.util.Scanner;

public class MiAhorcadoCliente {
    
    public static void main (String[] args) throws Exception
    {
        IMiAhorcado ORemoto = (IMiAhorcado) Naming.lookup("//10.142.105.196:80/MiAhorcado");
        Scanner sc = new Scanner(System.in);
        boolean fin = false;
        
        do
        {
            System.out.println("Introduzca una letra o palabra: ");
            String entrada = sc.nextLine();
            
            if (entrada.length() == 1)
            {
                System.out.println(ORemoto.enviarLetra(entrada.charAt(0)));
            }
            else
            {
               fin = ORemoto.enviarSolucion(entrada);
            }
        } while(!fin);
    }
}
