#include <iostream>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <thread>
#include <random>
#include <atomic>
#include <vector>
#include <ctime>

void calcularConvolucion(int **matriz, int kernel[3][3], int minLimite, int maxLimite)
{

}

int main(int argc, const char *argv[])
{
    int tamMatriz = 10;
    int op = -1;
    
    puts("¡Buenas! Bienvenido al programa para hacer convolución.");
    puts("Primero que nada, elija libremente el kernel de convolución que desea aplicar:");
    
    do
    {
        puts("1º Enfocar");
        puts("2º Realzar Bordes");
        puts("3º Detectar Border");
        puts("4º Filtro de Sobel");
        puts("5º Filtro de Sharpen");
        puts("Su opcion => ");
        
        std::cin >> op;
        
        if(op >= 1 && op <= 5) break;
        std::cout << "Opción incorrecta, escoja otra." << std::endl;
    } while (true);
    
    puts("Elija el numero de tareas para el procesamiento => ");
    int numeroHilos = 1;
    
    std::cin >> numeroHilos;
    
    int **matriz = new int *[tamMatriz];
    
    srand(time(NULL));
    for (int i = 0; i < tamMatriz; i++)
    {
        matriz[i] = new int[tamMatriz];
        for (int j = 0; j < tamMatriz; j++)
        {
            matriz[i][j] = (rand() % 40) - 20;
        }
    }
    
    for (int i = 0; i < tamMatriz; i++)
    {
        for (int j = 0; j < tamMatriz; j++)
        {
            std::cout << "| " << matriz[i][j] << " |";
        }
        std::cout << std::endl;
    }
    std::cout << "================================" << std::endl;
    
    int kernels[5][3][3] = {
        {
            {0, -1, 0}, {-1, 5, -1}, {0, -1, 0} // Enfoque
        },
        {
            {0, 0, 0}, {-1, 1, 0}, {0, 0, 0} // Realzar bordes
        },
        {
            {0, 1, 0}, {1, -4, 1}, {0, 1, 0} // Detectar bordes
        },
        {
            {-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1} // Filtro Sobel
        },
        {
            {1, -2, 1}, {-2, 5, -2}, {1, -2, 1} // Filtro Sharpen
        },
    };
    
    int min = 0;
    int max = 0;
    int ventana = tamMatriz / numeroHilos;
    std::vector<std::thread> hilos;
    
    for (int i = 0; i < numeroHilos; i++)
    {
        max += ventana;
        hilos.push_back(std::thread(calcularConvolucion, matriz, kernels[op], min, max));
        min += ventana;
    }
    
    if(max < tamMatriz)
        hilos.push_back(std::thread(calcularConvolucion, matriz, kernels[op], min, max));
    
    for(auto& thread : hilos)
    {
        thread.join();
    }
    
    for (int i = 0; i < tamMatriz; i++)
    {
        for (int j = 0; j < tamMatriz; j++)
        {
            std::cout << "| " << matriz[i][j] << " |";
        }
        std::cout << std::endl;
    }
    std::cout << "================================" << std::endl;

    return 0;
}


