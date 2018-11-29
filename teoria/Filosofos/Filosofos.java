public class Filosofos {
    
    public static void main (String[] args) {
        new Mesa();
    }
}

class Mesa {
    boolean[] tenedorLibre;
    
    public Mesa()
    {
        this(4);
    }
    
    public Mesa(int num)
    {
        tenedorLibre = new boolean[num + 1];
        for (int i = 0; i < this.tenedorLibre.length; i++)
        {
            this.tenedorLibre[i] = true;
        }
    }
    
    public synchronized void cogerTenedores(int i) throws Exception
    {
        while(!this.tenedorLibre[i] || !this.tenedorLibre[(i + 1) % this.tenedorLibre.length])
        {
            wait();
        }
        this.tenedorLibre[i] = this.tenedorLibre[(i + 1) % 5] = false;
    }
    
    public synchronized void soltarTenedores(int i) throws Exception
    {
        this.tenedorLibre[i] = this.tenedorLibre[(i + 1) % 5] = true;
        notifyAll();
    }
}

class Filosofo {
    
}
