/*
 * Eratos thenes using bit mask
 */
#include <iostream>
#include <cmath>

using namespace std;

const static int MAX_N = 1000;

unsigned char sieve[(MAX_N + 7) / 8];

inline bool isPrime(int k) {
    return sieve[k>>3] & (1 << (k & 7));
}

inline void setComposite(int k) {
    sieve[k>>3] &= ~(1 << (k & 7));
}

void eratosthenes(int n) {
    memset(sieve, 0xff, sizeof(sieve));
    setComposite(0);
    setComposite(1);
    int sqrtn = (int)sqrt(n);
    for(int i = 2; i <= sqrtn; ++i) {
        if (isPrime(i))
            for (int j=i*i; j <= n; j += i)
                setComposite(j);
    }
}

int main(int argc, char *argv[]) {
    eratosthenes(100);
    for (int i=0;i<100; i++)
        cout << i << " " << isPrime(i) << endl;
}
