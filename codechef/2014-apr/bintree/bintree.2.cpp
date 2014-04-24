// CODECHEF - Shortest Path in Binary Trees
// http://www.codechef.com/APRIL14/problems/BINTREE
 
#include <cstdio>
 
using namespace std;
 
void solve(int n1, int n2) {
    int length = 0;

    if (n1 == n2) {
        printf("0\n");
        return;
    }

    length = __builtin_clz(n1) - __builtin_clz(n2);

    n1 = n1 ^ (n2 >> length);

    printf("%d\n", length + 2 * (n1 ? (32 - __builtin_clz(n1)) : 0));
}
 
int main (int argc, char *argv[]) {
 
    int T;
    int n1, n2;
 
    scanf("%d", &T);
 
    while (T--) {
        scanf("%d %d", &n1, &n2);
        (n1 < n2) ? solve(n1, n2) : solve(n2, n1);
    }
 
}  