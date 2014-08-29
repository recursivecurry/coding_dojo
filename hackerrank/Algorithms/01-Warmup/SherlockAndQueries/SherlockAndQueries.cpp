// HACKERRANK: Sherlock And Queries
// https://www.hackerrank.com/challenges/sherlock-and-queries
#include <iostream>
#include <vector>
#include <cstdlib>
#include <cstdio>

using namespace std;

int main (int argc, char *argv[]) {
    long unsigned int N, M, count;
    long long val;
    vector<long long> A, B, C;
    
    scanf("%lu %lu", &N, &M);

    count = N;
    while (count--) {
        scanf("%lld", &val);
        A.push_back(val);
    }
    
    count = M;
    while (count--) {
        scanf("%lld", &val);
        B.push_back(val);
    }
    
    count = M;
    while (count--) {
        scanf("%lld", &val);
        C.push_back(val);
    }

    for (long unsigned int i=0; i<M; i++) {
        for (long unsigned int j=B[i]-1; j<N; j+=B[i]) {
            A[j] = (A[j] * C[i]) % 1000000007;
        }
    }

    for (auto it : A) {
        cout << it << " ";
    }
}
