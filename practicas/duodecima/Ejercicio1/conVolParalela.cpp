#include <iostream>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <thread>
#include <random>
#include <atomic>
#include <vector>
#include <ctime>

void calcularConvolucion(int **matriz, int tamMatriz, int kernel[3][3], int tamKernel, int minLimite, int maxLimite)
{
    for (int i = 0; i < tamMatriz; i++)
    {
        for (int j = minLimite; j < maxLimite && j < tamMatriz; j++)
        {
            int mm[tamKernel][tamKernel];
            int centroFila    = (tamKernel/ 2);
            int centroColumna = (tamKernel/ 2);
            mm[centroFila][centroColumna] = matriz[i][j];
            
            int z = 0, x = 0;
            for (int k = i - centroFila; k <= i + centroFila; k++)
            {
                for (int p = j - centroColumna; p <= j + centroColumna; p++)
                {
                    if(k < 0 || p < 0 || k >= tamMatriz || p >= tamMatriz)
                    {
                        mm[z][x++] = 0;
                    } else
                    {
                        mm[z][x++] = matriz[k][p];
                    }
                }
                z++; x = 0;
            }
            
            int total = 0;
            for (int k = 0; k < tamKernel; k++)
            {
                for (int p = 0; p < tamKernel; p++)
                {
                    total += (mm[k][p] * kernel[k][p]);
                }
            }
            
            matriz[i][j] = total;
        }
    }
}

int main(int argc, const char *argv[])
{
    srand(time(NULL));
    int tamMatriz = 10;
    int op = -1;
    int numeroHilos = 1;
    
    puts("¡Buenas! Bienvenido al programa para hacer convolución.");
    puts("Primero que nada, elija libremente el kernel de convolución que desea aplicar:");
    
    do
    {
        puts("1º Enfocar");
        puts("2º Realzar Bordes");
        puts("3º Detectar Border");
        puts("4º Filtro de Sobel");
        puts("5º Filtro de Sharpen");
        std::cout << ("Su opcion => ");
        
        std::cin >> op;
        
        if(op >= 1 && op <= 5) break;
        std::cout << "Opción incorrecta, escoja otra." << std::endl;
    } while (true);
    
    std::cout << ("Elija el numero de tareas para el procesamiento => ");
    
    std::cin >> numeroHilos;
    std::cout << ("¿Desea usar un matriz de prueba o una dinámica? (prueba/dinamica) (p/d) => ");
    
    std::string eleccion;
    std::cin >> eleccion;
    
    int matriz_prueba[tamMatriz][tamMatriz] =
    {
        {-14, 5  , -5 , 5  , -11, -15, 7  , 18 , -6 , -12}, // 1
        {-8 , -3 , 17 , -5 , 5  , -11, -2 , -9 , -18, 3  }, // 2
        {2  , 4  , -4 , 4  , 6  , 2  , 16 , 1  , 6  , -8 }, // 3
        {-5 , 4  , 9  , 2  , -18, 19 , 0  , 9  , 9  , 6  }, // 4
        {9  , -19, -17, -1 , -12, 8  , 0  , 7  , -20, -6 }, // 5
        {-18, -6 , 19 , -2 , 10 , -3 , 13 , 6  , 18 , -1 }, // 6
        {11 , -15, -17, 0  , 0  , 17 , -9 , -20, -2 , -20}, // 7
        {6  , -12, 13 , 10 , 7  , -18, -10, -13, 1  , -18}, // 8
        {2  , 3  , -4 , -7 , 14 , 19 , 2  , 7  , 5  , -8 }, // 9
        {-14, -12, -2 , -19, 9  , 10 , 11 , 12 , 10 , -11}, // 10
    };
    
    int **matriz = new int *[tamMatriz];
    for (int i = 0; i < tamMatriz; i++)
    {
        matriz[i] = new int[tamMatriz];
        for (int j = 0; j < tamMatriz; j++)
        {
            if(eleccion.compare("prueba") || eleccion.compare("p"))
            {
                matriz[i][j] = matriz_prueba[i][j];
            } else
            {
                matriz[i][j] = (rand() % 40) - 20;
            }
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
            {0, 0, 0}, {-1, 1, 0}, {0, 0, 0}    // Realzar bordes
        },
        {
            {0, 1, 0}, {1, -4, 1}, {0, 1, 0}    // Detectar bordes
        },
        {
            {-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}  // Filtro Sobel
        },
        {
            {1, -2, 1}, {-2, 5, -2}, {1, -2, 1} // Filtro Sharpen
        },
    };
    
    int min = 0, max = 0, ventana = tamMatriz / numeroHilos;
    std::vector<std::thread> hilos;
    
    for (int i = 0; i < numeroHilos; i++)
    {
        max += ventana;
        hilos.push_back(std::thread(calcularConvolucion, matriz, tamMatriz, kernels[op], 3, min, max));
        min += ventana;
    }
    
    if(max < tamMatriz)
        hilos.push_back(std::thread(calcularConvolucion, matriz, tamMatriz, kernels[op], 3, min, max));
    
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


