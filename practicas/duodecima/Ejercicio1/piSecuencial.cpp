#include <iostream>
#include <cstring>
#include <algorithm>
#include <cstdlib>
#include <cmath>
#include <atomic>

using namespace std;

double aciertos = 0;
mutex m;

double getNumAleat(double a, double b) {
    return (a + ((double) rand() / RAND_MAX) * (b - a));
}

void hacerAprox(int puntos) {
    m.lock();
    for (int i = 0; i < puntos; i++) {
        double x = getNumAleat(0, 1) * 2 - 1;
        double y = getNumAleat(0, 1) * 2 - 1;
        double z = x*x + y*y;

        if(z <= 1) {
            aciertos++;
        }
    }
    m.unlock();
    // return (aciertos * 4) / puntos;
}

int main(int argc, const char *argv[]) {

    srand(time(0));



    double aproxPi = hacerAprox(10000);
    cout << "La aproximación a pi es => " << aproxPi << endl;

    return 0;
}
