#include <iostream>
#include <thread>
#include <unistd.h>

int main(int argc, const char *argv[])
{
    //std::thread hilo([] () { while(true) std::cout << "periquito de los palotes" << std::endl; });
    std::thread hilo([] () { int i; while(true) i++; });
    
    //sleep(5);
    usleep(5);
    
    return 0;
}
