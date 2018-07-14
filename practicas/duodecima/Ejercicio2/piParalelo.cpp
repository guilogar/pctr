#include <iostream>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <thread>
#include <random>
#include <atomic>
#include <vector>
#include <fstream>

#define N 1000
#define numeroHilos 7

using namespace std;
ofstream tiempo;
ofstream speed_up;
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
    tiempo.open("info/cpp_tiempo.txt", ios::trunc);
    speed_up.open("info/cpp_speed_up.txt", ios::trunc);
    
    //Tiempo una hebra.
    chrono::time_point<std::chrono::system_clock> start, end;
    start = chrono::system_clock::now();
    
    thread t (hacerAproximacion, N);
    t.join();
    
    end = chrono::system_clock::now();
    chrono::duration<double> elapsedUnaHebra = end-start;
    cout << "Tiempo con 1 hebra: " << elapsedUnaHebra.count() << "s\n";
    
    tiempo << 1 << " " << elapsedUnaHebra.count() << endl;
    speed_up << 1 << " " << (elapsedUnaHebra.count()/elapsedUnaHebra.count()) << endl;
    
    //Tiempo MultiHebra.
    for (int i = 2; i <= numeroHilos; i++) {
        aciertos.store(0);
        vector<thread> hilos;
        
        for (int j = 0; j < i; j++) {
            hilos.push_back(thread(hacerAproximacion, N / i));
        }
        
        start = chrono::system_clock::now();
        
        for(auto& thread : hilos) {
            thread.join();
        }
        
        end = chrono::system_clock::now();
        chrono::duration<double> elapsedMultiHebra = end-start;
        cout << "Tiempo con " << i << " hebras: " << elapsedMultiHebra.count() << "s\n";
        
        tiempo << i << " " << elapsedMultiHebra.count() << endl;
        speed_up << i << " " << (elapsedUnaHebra.count()/elapsedMultiHebra.count()) << endl;
    }
    
    double aproxPi = getPi(N);
    cout << "La aproximación a pi es => " << aproxPi << endl;
    
    return 0;
}
