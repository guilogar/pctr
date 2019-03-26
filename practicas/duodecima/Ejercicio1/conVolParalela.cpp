#include <iostream>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <thread>
#include <random>
#include <atomic>
#include <vector>

using namespace std;
int calcularConvolucion(vector<vector<int>> kernel, int minLimite, int maxLimite);

int main(int argc, const char *argv[])
{
    vector<thread> hilos;
    vector<vector<int>> kernel();
    
    int tamMatriz = 10;
    int numeroHilos = 10;
    int min = 0;
    int max = 0;
    int ventana = tamMatriz / numeroHilos;
    for (int i = 0; i < numeroHilos; i++)
    {
        max += ventana;
        hilos.push_back(thread(calcularConvolucion, kernel, min, max));
        min += ventana;
    }
    
    for(auto& thread : hilos) {
        thread.join();
    }

    return 0;
}
