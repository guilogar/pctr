public class Hilo extends Thread {
    
    private Monitor m;
    private int tipo;
    
    public Hilo(Monitor m, int tipo)
    {
        this.m = m;
        this.tipo = tipo;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            switch (this.tipo) {
                case 1: {
                    this.m.reservarr1(5);
                    System.out.println("He reservado 5");
                    this.m.liberarr1(5);
                    System.out.println("He liberado 5");
                }; break;
                case 2: {
                    this.m.reservarr2(5);
                    System.out.println("He reservado 5");
                    this.m.liberarr2(5);
                    System.out.println("He liberado 5");
                }; break;
                case 3: {
                    this.m.reservarr1r2(5, 5);
                    System.out.println("He reservado 5 y 5");
                    this.m.liberarr1(5);
                    this.m.liberarr2(5);
                    System.out.println("He liberado 5 y 5");
                }; break;
                
                default: break;
            }
        }
    }
}
