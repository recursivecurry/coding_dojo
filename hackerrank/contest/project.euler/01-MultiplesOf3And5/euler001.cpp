// HACKERRANK: Project Euler #1 Multiples of 3 and 5
// https://www.hackerrank.com/contests/projecteuler/challenges/euler001
#include <iostream>

using namespace std;

long long solve (long long n) {

    long long div = n / 15;
    long long mod = n % 15;
    long long sum = (60 * div) + ((div-1)*div/2*105);

    for (long long pos=n-mod+1; pos<n; pos++)
        if (pos%3==0 || pos%5==0)
            sum += pos;
    return sum;
}

int main (int argc, char *argv[]) {

    int T;
    long long N;

    cin >> T;

    while (T--) {
        cin >> N;
        cout << solve(N) << endl;
    }

    return 0;
}