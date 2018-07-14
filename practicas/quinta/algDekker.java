class algDekker {
    static volatile int inCS = 0;
    static volatile boolean wantp = false;
    static volatile boolean wantq = false;
    static volatile boolean wantr = false;
    static volatile int turn = 1;
    
    // Cambio la condición en el if, haciendo una implementación 
    // un tanto distinta a la tradicional de Dekker.
    class Q extends Thread {
        public void run() {
            while (true) {
                /* Non-critical section */
                wantq = true;
                while (wantp || wantr) {
                    if (turn != 1) {
                        wantq = false;
                        while (turn == 1)
                            Thread.yield();
                        wantq = true;
                    }
                }
                inCS++;
                Thread.yield();
                /* Critical section */
                System.out.println("Number of processes in critical section: " + inCS);
                inCS--;
                turn = 2;
                wantq = false;
            }
        }
    }
    
    class P extends Thread {
        public void run() {
            while (true) {
                /* Non-critical section */
                wantp = true;
                while (wantq || wantr) {
                    if (turn != 2) {
                        wantp = false;
                        while (turn == 2)
                            Thread.yield();
                        wantp = true;
                    }
                }
                inCS++;
                Thread.yield();
                /* Critical section */
                System.out.println("Number of processes in critical section: " + inCS);
                inCS--;
                turn = 3;
                wantp = false;
            }
        }
    }
    
    class R extends Thread {
        public void run() {
            while (true) {
                /* Non-critical section */
                wantr = true;
                while (wantp || wantq) {
                    if (turn != 3) {
                        wantr = false;
                        while (turn == 3)
                            Thread.yield();
                        wantr = true;
                    }
                }
                inCS++;
                Thread.yield();
                /* Critical section */
                System.out.println("Number of processes in critical section: " + inCS);
                inCS--;
                turn = 1;
                wantr = false;
            }
        }
    }
    
    algDekker() {
        Thread p = new P();
        Thread q = new Q();
        Thread r = new R();
        q.start();
        p.start();
        r.start();
    }
    
    public static void main(String[] args) {
        new algDekker();
    }
}
