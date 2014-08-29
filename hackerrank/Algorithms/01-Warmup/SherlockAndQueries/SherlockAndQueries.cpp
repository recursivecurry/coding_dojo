// HACKERRANK: Sherlock And Queries
// https://www.hackerrank.com/challenges/sherlock-and-queries
#include <iostream>
#include <vector>

using namespace std;

int main (int argc, char *argv[]) {
    size_t N, M, count;
    long long val;
    vector<long long> A, B, C;
    
    cin >> N >> M;

    count = N;
    while (count--) {
        cin >> val;
        A.push_back(val);
    }
    
    count = M;
    while (count--) {
        cin >> val;
        B.push_back(val);
    }
    
    count = M;
    while (count--) {
        cin >> val;
        C.push_back(val);
    }

    for (size_t i=0; i<M; i++) {
        for (size_t j=0; j<N; j++) {
            if ( (j+1) % B[i] == 0 )
                A[j] = (A[j] * C[i]) % 1000000007;
        }
    }

    for (auto it : A) {
        cout << it << " ";
    }
}
