import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.Semaphore;

public class ficheroSeguro
{
    public static void main (String[] args) {
        Semaphore s = new Semaphore(1);
        String ficherito = "";
        
        try
        {
            ficherito = args[0];
        } catch(Exception e) { }
        
        for (int i = 0; i < 10; i++)
        {
            Lector l = new Lector(s, ficherito);
            Escritor e = new Escritor(s, ficherito);
            l.start();
            e.start();
        }
    }
}

class Lector extends Thread
{
    Semaphore s;
    private String nombreFichero;
    
    public Lector(Semaphore s, String nombreFichero)
    {
        this.s = s;
        this.nombreFichero = nombreFichero;
    }
    
    @Override
    public void run()
    {
        this.leer();
    }
    
    private void leer()
    {
        try {
            this.s.acquire();
            
            // Para leer en exclusión mutua
            File ruta = new File(this.nombreFichero);
            try
            {
                RandomAccessFile fichero = new RandomAccessFile(ruta, "r");
                
                while(fichero.getFilePointer() <= fichero.length())
                System.out.println(fichero.readLine());
                fichero.close();
            } catch (EOFException e) { }
              catch (Exception e) { }
            
            this.s.release();
        } catch(Exception e) { }
    }
}

class Escritor extends Thread
{
    Semaphore s;
    private String nombreFichero;
    
    public Escritor(Semaphore s, String nombreFichero)
    {
        this.s = s;
        this.nombreFichero = nombreFichero;
    }
    
    @Override
    public void run()
    {
        this.escribir("holi");
    }
    
    private void escribir(String w)
    {
        try {
            this.s.acquire();
            
            // Para escribir en exclusión mutua
            File ruta = new File(this.nombreFichero);
            try
            {
                RandomAccessFile fichero = new RandomAccessFile(ruta, "rw");
                
                fichero.writeUTF(w);
                fichero.close();
            } catch (IOException e) { }
            
            this.s.release();
        } catch(Exception e) { }
    }
}
