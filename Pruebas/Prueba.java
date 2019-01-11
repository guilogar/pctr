public class Prueba extends Thread
{
    @Override
    public void run()
    {
        while(true)
            System.out.println("kndgljsnljg njlksang kjlsnjkg ");
    }
    
    public static void main (String[] args) {
        Prueba p = new Prueba();
        p.start();
        //System.exit(-1);
    }
}
