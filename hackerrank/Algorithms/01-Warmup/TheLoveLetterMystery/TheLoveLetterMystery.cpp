// HACKERRANK - The Love-Letter Mystery
// https://www.hackerrank.com/challenges/the-love-letter-mystery
#include <cstdio>
#include <cstring>
#include <cstdlib>

using namespace std;

int main (int argc, char *argv[]) {
    int T = 0;
    char str[10001] = {0, };

    scanf("%d", &T);

    while (T--) {
        int count=0;
        int st = 0, ed=0;

        scanf("%s", str);
        ed = strlen(str)-1;

        while (st < ed) {
            count += abs(str[st]-str[ed]);
            st++;
            ed--;
        }
        printf("%d\n", count);
    }

    return 0;
}
