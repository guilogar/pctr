import java.util.concurrent.Semaphore;

public class ficheroSeguro
{
    public static void main (String[] args) {
        Semaphore s = new Semaphore(1);
        
        for (int i = 0; i < 10; i++)
        {
            Lector l = new Lector(s);
            Escritor e = new Escritor(s);
            l.start();
            e.start();
        }
    }
}

class Lector extends Thread
{
    Semaphore s;
    public static int numLectores = 0;
    
    public Lector(Semaphore s)
    {
        this.s = s;
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
            numLectores++;
            System.out.println("Leyendo....");
            numLectores--;
            System.out.println(numLectores);
            this.s.release();
        } catch(Exception e) { }
    }
}

class Escritor extends Thread
{
    Semaphore s;
    public static int numEscritores = 0;
    
    public Escritor(Semaphore s)
    {
        this.s = s;
    }
    
    @Override
    public void run()
    {
        this.escribir("");
    }
    
    private void escribir(String w)
    {
        try {
            this.s.acquire();
            numEscritores++;
            System.out.println("Escribiendo....");
            numEscritores--;
            System.out.println(numEscritores);
            this.s.release();
        } catch(Exception e) { }
    }
}
