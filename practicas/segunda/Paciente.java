import java.lang.*;
import java.util.*;

public class Paciente {
    
    private String nombre;
    private String dni;
    private String direccion;
    private String compSeguros;
    private String sexo;
    private int edad;
    private int telefono;
    
    public Paciente() {
        
    }
    
    public Paciente(String nombre, String dni, String direccion, String compSeguros,
                    String sexo, int edad, int telefono) {
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.compSeguros = compSeguros;
        this.sexo = sexo;
        this.edad = edad;
        this.telefono = telefono;
    }
    
    public Paciente(String nombre, String dni, String sexo, int edad, int telefono) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
        this.edad = edad;
        this.telefono = telefono;
    }
    
    public String getDni() {
        return this.dni;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String compSeguros() {
        return this.compSeguros;
    }
    
    public String getSexo() {
        return this.sexo;
    }
    
    public int getEdad() {
        return this.edad;
    }
    
    public int getTelefono() {
        return this.telefono;
    }
}

