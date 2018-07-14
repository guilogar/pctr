import java.rmi.Naming;

public class cBonoloto {
    
    public static void main (String[] args) throws Exception {
        
        iBonoloto RefBonoRemoto = (iBonoloto) Naming.lookup("//localhost/ServerBonoloto");
        int[] apuesta = {1,2,3,4,5,6};
        
        if(RefBonoRemoto.comprobarApuesta(apuesta)) {
            System.out.println("wiii, soy riiiicooooooo!!!!");
        } else {
            System.out.println("Pues nada, a seguir trabajando por desgracia =(.");
        }
    }
}
