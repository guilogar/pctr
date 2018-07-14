#include <iostream>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <thread>
#include <random>
#include <atomic>
#include <vector>

#define N 1000
#define numeroHilos 10

using namespace std;
//recursive_mutex mutex;

atomic<int> aciertos;

double getNumAleat(double a, double b) {
    random_device rd;
    mt19937 gen(rd());
    uniform_real_distribution<> dis(a, b);
    return dis(gen);
}

void hacerAproximacion(int puntos) {
    //lock_guard<recursive_mutex> cerrojo(mutex);
    
    for (int i = 0; i < puntos; i++) {
        double x = getNumAleat(0, 1) * 2 - 1;
        double y = getNumAleat(0, 1) * 2 - 1;
        double z = x*x + y*y;
        
        if(z <= 1) {
            ++aciertos;
        }
    }
}

double getPi(int puntos) {
    return (double) (aciertos.load() * 4) / puntos;
}

int main(int argc, const char *argv[]) {
    aciertos.store(0);
    vector<thread> hilos;
    
    for (int i = 0; i < numeroHilos; i++) {
        hilos.push_back(thread(hacerAproximacion, N));
    }
    
    for(auto& thread : hilos) {
        thread.join();
    }
    
    double aproxPi = getPi(N * numeroHilos);
    cout << "La aproximación a pi es => " << aproxPi << endl;
    
    return 0;
}
