import java.net.*;
import java.io.*;

public class clienteMultiple
{
    public static void main (String[] args)
    {
        for (int nTareas = 1; nTareas < 200; nTareas++)
        {
            double initTiempoConPool = System.currentTimeMillis();
            for (int j = 0; j < nTareas; j++) // Bucle para peticiones al servidor con pool de hebras
            {
                int i = (int)(Math.random()*10), puerto = 2001;
                try
                {
                    System.out.println("Realizando conexion...");
                    Socket cable = new Socket("localhost", puerto);
                    System.out.println("Realizada conexion a "+cable);
                    PrintWriter salida= new PrintWriter(
                                            new BufferedWriter(
                                                new OutputStreamWriter(
                    cable.getOutputStream())));
                    salida.println(i);
                    salida.flush();
                    System.out.println("Cerrando conexion...");
                    cable.close();
                    
                }//try
                catch (Exception e) {System.out.println("Error en sockets...");}
            }
            double tiempoTotalConPool = (System.currentTimeMillis() - initTiempoConPool);
            utilsFile.writeInFile("info", "servidorconpool.txt", ""+nTareas+" "+tiempoTotalConPool+"\n");
            
            double initTiempoSinPool = System.currentTimeMillis();
            for (int j = 0; j < nTareas; j++) // Bucle para peticiones al servidor sin pool de hebras
            {
                int i = (int)(Math.random()*10), puerto = 2002;
                try
                {
                    System.out.println("Realizando conexion...");
                    Socket cable = new Socket("localhost", puerto);
                    System.out.println("Realizada conexion a "+cable);
                    PrintWriter salida= new PrintWriter(
                                            new BufferedWriter(
                                                new OutputStreamWriter(
                    cable.getOutputStream())));
                    salida.println(i);
                    salida.flush();
                    System.out.println("Cerrando conexion...");
                    cable.close();
                    
                }//try
                catch (Exception e) {System.out.println("Error en sockets...");}
            }
            double tiempoTotalSinPool = (System.currentTimeMillis() - initTiempoSinPool);
            utilsFile.writeInFile("info", "servidorsinpool.txt", ""+nTareas+" "+tiempoTotalSinPool+"\n");
        }
    }//main
}
