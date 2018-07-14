import java.lang.*;
import java.util.*;

public class Biblioteca {
    
    public static void main (String[] args) {
        Paciente[] pacientes = new Paciente[10];
        int num_pacientes = 0;
        int op = 0;
        
        do {
            System.out.println("Menú de Usuario");
            System.out.println("===============");
            System.out.println();
            System.out.println("1º) Añadir Paciente.");
            System.out.println("2º) Borrar Paciente.");
            System.out.println("3º) Consultar lista de pacientes.");
            System.out.println("4º) Salir.");
            System.out.print("Elija su opción => ");
            
            try {
                Scanner sc = new Scanner(System.in);
                op = sc.nextInt();
            } catch(Exception e) {
                System.err.println("Debe usted introducir un número. Vuelva a intentarlo.");
                continue;
            }
            
            switch (op) {
                case 1: {
                            for (int i = 0; i < pacientes.length; ++i) {
                                if(pacientes[i] == null) {
                                    pacientes[i] = crearPaciente();
                                    System.out.println("\nPaciente creado.\n");
                                    num_pacientes++;
                                    break;
                                }
                            }
                            if(num_pacientes == pacientes.length)
                                System.err.println("Limites de pacientes superado.");
                        };
                        break;
                case 2: {
                            borrarPaciente(pacientes);
                        };
                        break;
                case 3: {
                            consultarPacientes(pacientes);
                        };
                        break;
                case 4: 
                        System.out.println("Bye.");
                        return;
                default:{
                        System.err.println("Opción incorrecta. Vuelva a intentarlo");
                        continue;
                        }
            }
        } while ( op != 4 );
    }
    
    public static Paciente crearPaciente() {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Digame el nombre del paciente => ");
        String nombre = sc.nextLine();
        
        System.out.print("Digame el dni del paciente => ");
        String dni = sc.nextLine();
        
        System.out.print("Digame el sexo del paciente => ");
        String sexo = sc.nextLine();
        
        System.out.print("Digame la edad del paciente => ");
        int edad = sc.nextInt();
        
        System.out.print("Digame el telefono del paciente => ");
        int telefono = sc.nextInt();
        
        return new Paciente(nombre, dni, sexo, edad, telefono);
    }
    
    public static void borrarPaciente(Paciente[] pacientes) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Digame el nombre del paciente a borrar => ");
        String nombre = sc.nextLine();
        
        System.out.print("Digame el dni del paciente a borrar => ");
        String dni = sc.nextLine();
        
        for (int i = 0; i < pacientes.length; ++i) {
            if(pacientes[i].getNombre().equals(nombre) ||
               pacientes[i].getDni().equals(dni)) {
                pacientes[i] = null;
                System.out.println("\nPaciente borrado.\n");
                break;
            }
        }
    }
    
    public static void consultarPacientes(Paciente[] pacientes) {
        System.out.println();
        System.out.println("Lista de pacientes");
        System.out.println("==================");
        System.out.println();
        
        if(pacientes.length == 0) {
            System.out.println("Aun no hay pacientes.");
            return;
        }
        for (int i = 0; i < pacientes.length; ++i) {
            if(pacientes[i] != null) {
                System.out.println((i + 1) + "º Paciente. Nombre: " + pacientes[i].getNombre() + 
                                   ", Dni: " + pacientes[i].getDni() + ", Sexo: " +
                                   pacientes[i].getSexo() + ", Edad: " + 
                                   pacientes[i].getEdad());
                System.out.println("------------------------------------------------------");
            }
        }
        System.out.println();
    }
}























