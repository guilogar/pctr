#include <iostream>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <thread>
#include <random>
#include <vector>
#include <fstream>

#include "pcMonitor.cpp"

#define numeroHilos 7

using namespace std;
ofstream tiempo;

void consumer(int id, BoundedBuffer& buffer){
    int value = buffer.fetch();
    std::cout << "Consumer " << id << " fetched " << value << std::endl;
    std::this_thread::sleep_for(std::chrono::milliseconds(250));
}

void producer(int id, BoundedBuffer& buffer){
    int i = buffer.deposit();
    std::cout << "Produced " << id << " produced " << i << std::endl;
    std::this_thread::sleep_for(std::chrono::milliseconds(100));
}

int main(int argc, const char *argv[]) {
    tiempo.open("info/cpp_tiempo.txt", ios::trunc);
    BoundedBuffer buffer(200);
    
    //Tiempo de un p-c.
    chrono::time_point<std::chrono::system_clock> start, end;
    start = chrono::system_clock::now();
    
    thread c1(consumer, 0, ref(buffer));
    thread p1(producer, 0, ref(buffer));
    p1.join();
    c1.join();
    
    end = chrono::system_clock::now();
    chrono::duration<double> elapsedUnaHebra = end-start;
    cout << "Tiempo con 1 hebra: " << elapsedUnaHebra.count() << "s\n";
    
    tiempo << 1 << " " << elapsedUnaHebra.count() << endl;
    
    //Tiempo para 2 o mas p-c.
    for (int i = 2; i <= numeroHilos; i++) {
        vector<thread> hilos;
        
        for (int j = 0; j < i; j++) {
            hilos.push_back(thread(consumer, j+1, ref(buffer)));
            hilos.push_back(thread(producer, j+1, ref(buffer)));
        }
        
        start = chrono::system_clock::now();
        
        for(auto& thread : hilos) {
            thread.join();
        }
        
        end = chrono::system_clock::now();
        chrono::duration<double> elapsedMultiHebra = end-start;
        cout << "Tiempo con " << i << " hebras: " << elapsedMultiHebra.count() << "s\n";
        
        tiempo << i << " " << elapsedMultiHebra.count() << endl;
    }
    
    return 0;
}
