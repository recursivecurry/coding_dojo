// HACKERRANK: Is Fibo
// https://www.hackerrank.com/challenges/is-fibo
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<long long> fibonum;

void increase(long long target) {
    while (fibonum.back() < target) {
        size_t size = fibonum.size();
        fibonum.push_back(fibonum[size-1]+fibonum[size-2]); 
    }
}

bool check(long long target) {
    return fibonum.end() == find(fibonum.begin(), fibonum.end(), target);
}

int main (int argc, char *argv[]) {
    int T;
    cin >> T;
    fibonum.push_back(0);
    fibonum.push_back(1);
    while (T--) {
        long long N;
        cin >> N;
        increase(N);
        cout << (check(N)? "IsNotFibo" : "IsFibo") << endl;
        
    }
}
