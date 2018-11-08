
public class HilosYield extends Thread {
    public static HilosYield[] hilos = new HilosYield[10];
    public boolean haFinalizado = false;
    
    @Override
    public void run() {
        //if(this.getName().equals("Thread-5")) Thread.yield();
        
        if(this.getName().equals("Thread-5"))
        {
            boolean todosFinalizados = true;
            
            do
            {
                for (int i = 0; i < hilos.length; i++)
                {
                    if(hilos[i] != null && !hilos[i].haFinalizado && !hilos[i].getName().equals("Thread-5"))
                    {
                        todosFinalizados = false;
                    }
                }
                
                if(!todosFinalizados) Thread.yield();
            } while(!todosFinalizados);
        }
        
        System.out.println("Hilo " + getName() + " finaliza run.");
        haFinalizado = true;
    }
    
    public static void main (String[] args) throws Exception {
        
        Thread.currentThread().setPriority(10);
        
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new HilosYield();
            hilos[i].start();
        }
        
        /*
         *for (int i = 0; i < hilos.length; i++) {
         *    hilos[i].join();
         *}
         */
        
        System.out.println("Finaliza el programa.");
    }
}
