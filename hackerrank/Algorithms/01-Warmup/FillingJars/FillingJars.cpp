// HACKERRANK - Filling Jars
// https://www.hackerrank.com/challenges/filling-jars
#include <iostream>

using namespace std;

int main (int argc, char *argv[]) {
    long long N, M, SUM=0;

    cin >> N >> M;
    
    while (M--) {
        long long a, b, k;
        cin >> a >> b >> k;
        SUM += ((b-a+1)*k);
    }
    cout << (SUM / N) << endl;
}
