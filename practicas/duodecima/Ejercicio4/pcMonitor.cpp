#include <thread>
#include <mutex>
#include <condition_variable>

struct BoundedBuffer {
    int cont;
    int capacity;
    std::mutex lock;
    
    std::condition_variable not_full;
    std::condition_variable not_empty;
    
    BoundedBuffer(int capacity) : capacity(capacity), cont(0) {}
    
    ~BoundedBuffer(){ }
    
    int deposit(){
        std::unique_lock<std::mutex> l(lock);
        not_full.wait(l, [this](){return cont != capacity; });
        ++cont;
        not_empty.notify_one();
        return cont;
    }
    
    int fetch(){
        std::unique_lock<std::mutex> l(lock);
        not_empty.wait(l, [this](){return cont != 0; });
        --cont;
        not_full.notify_one();
        return cont;
    }
};

