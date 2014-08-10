// HACKERRANK: Find Digits
// https://www.hackerrank.com/challenges/find-digits
#include <iostream>
#include <set>

using namespace std;

int solve(long long n) {
    int count=0;
    long long orig = n;
    while (n) {
        long long r = n % 10;
        if (r && (orig % r == 0))
            count++;
        n /= 10;
    }
    return count;
}

int main(int argc, char *argv[]) {
    int N;
    cin >> N;
    while (N--) {
        long long val;
        cin >> val;
        cout << solve(val) << endl;
    }
}