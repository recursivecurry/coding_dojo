// HACKERRANK - Game Of Thrones - I
// https://www.hackerrank.com/challenges/game-of-thrones
#include <cstdio>
#include <cstdlib>
#include <cstring>

int main (int argc, char *argv[]) {

    char input[100001] = {0, };
    int alphabet[27] = {0, };
    int len = 0, sum=0;

    scanf("%s", input);

    len = strlen(input);

    for (int i=0; i<len; i++) {
        alphabet[input[i]-'a']++;
    }
    for (int j=0; j<26; j++) {
        sum += (alphabet[j]%2);
    }
    if (sum<=1)
        printf("YES\n");
    else
        printf("NO\n");
}