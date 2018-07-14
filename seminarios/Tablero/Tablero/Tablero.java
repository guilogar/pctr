package Tablero;

import java.lang.*;
import java.util.*;

public class Tablero {
    char[][] tablero = null;
    int huecosDisponibles;
    
    public Tablero(int filas, int columnas) {
        this.huecosDisponibles = filas * columnas;
        this.tablero = new char[filas][columnas];
        
        for (int i = 0; i < this.tablero.length; ++i) {
            for (int j = 0; j < this.tablero[i].length; ++j) {
                this.tablero[i][j] = ' ';
            }
        }
    }
    
    public boolean ganador() {
        return false;
    }
    
    public boolean espacio() {
        return huecosDisponibles != 0;
    }
    
    public void imprimir() {
        for (int i = 0; i < this.tablero.length; ++i) {
            System.out.println("---------------------");
            for (int j = 0; j < this.tablero[i].length; ++j) {
                System.out.print("|" + this.tablero[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------------------");
    }
    
    public boolean huecoLibre(int columna) {
        return this.tablero[0][columna] == ' ';
    }
    
    public void introducirFicha(int columna, char simbolo) {
        int filaColocarFicha = this.tablero.length - 1;
        
        while (this.tablero[filaColocarFicha][columna] != ' ' && filaColocarFicha >= 0) {
            --filaColocarFicha;
        }
        if (filaColocarFicha < 0) {
            System.out.println("Lo sentimos, en esta columna no queda hueco.");
            return;
        }
        this.tablero[filaColocarFicha][columna] = simbolo;
    }
}
