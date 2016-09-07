// HACKERRANK: Sherlock and GCD
// https://www.hackerrank.com/challenges/sherlock-and-gcd
#include <iostream>
#include <vector>

using namespace std;

int gcd(int p, int q) {
    if (q==0) return p;
    return gcd(q, p%q);
}

bool solve(vector<int> &a) {
    int val;
    while ( !a.empty() ) {
        val = a.back();
        a.pop_back();

        for (vector<int>::iterator it = a.begin(); it != a.end(); it++) {
            if (gcd(val, *it) == 1)
                return true;
        }
    }

    if (val == 1)
        return true;
    else
        return false;
}

int main (int argc, char *argv[]) {
    int T;

    cin >> T;

    while (T--) {
        int N;
        vector<int> A;
        cin >> N;
        while (N--) {
            int val;
            cin >> val;
            A.push_back(val);
        }
        cout << (solve(A) ? "YES" : "NO") << endl;
    }
    return 0;
}