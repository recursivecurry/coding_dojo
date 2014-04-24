// CODECHEF - Shortest Path in Binary Trees
// http://www.codechef.com/APRIL14/problems/BINTREE
 
#include <iostream>
 
using namespace std;
 
int solve(int n1, int n2) {
    int length = 0;
    int t1 = n1, t2 = n2;

    if (n1 == n2)
        return 0;

    while (t1) {
        t1 = t1 >> 1;
        t2 = t2 >> 1;
    }

    while (t2) {
        length++;
        t2 = t2 >> 1;
    }

    n2 = n2 >> length;

    while (n1 != n2) {
        n1 = n1 >> 1;
        n2 = n2 >> 1;
        length += 2;
    }

    return length;
}
 
int main (int argc, char *argv[]) {
 
    int T;
    int n1, n2;
 
    ios_base::sync_with_stdio(false);
    cin >> T;
 
    while (T--) {
        cin >> n1 >> n2;
        if (n1 < n2)
            cout << solve(n1, n2) << endl;
        else
            cout << solve(n2, n1) << endl;
    }
 
}  