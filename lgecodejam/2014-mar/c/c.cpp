#include <cstdio>
#include <cstring>


int N = 0;
char WORD[1000][1001] = {0, };


void find_shortest(size_t target, int result[1000][1000]) {
    size_t length = strlen(WORD[target]);

    for (size_t len=1; len<=length; len++) {
        for (size_t pos=len-1; pos<length; pos++) {
            bool success = true;

            for (size_t other=0; other<N; other++) {
                if (target!=other) {
                    if (len <= result[other][pos]) {
                        success = false;
                        break;
                    }
                }
            }
            if (success) {
                printf("%zu\n", len);
                return;
            }

        }
    }
}

void process(size_t target, size_t other, int result[]) {

    size_t target_length = strlen(WORD[target]);
    size_t other_length = strlen(WORD[other]);
    int mark[1000][1000] = {0, };

    for (size_t i=0; i<target_length; i++) {
        int max=0;
        for (size_t j=0; j<other_length; j++) {
            if (WORD[target][i] != WORD[other][j]) {
                mark[i][j] = 0;
            } else if ((0==i)||(0==j)) {
                mark[i][j] = 1;
                max = 1;
            } else {
                mark[i][j] = mark[i-1][j-1]+1;
                if (max < mark[i][j]) {
                    max = mark[i][j];
                }
            }
        }
        result[i] = max;
    }
}

int main() {
    scanf("%d", &N);
    for (int i=0; i<N; i++) {
        scanf("%s", WORD[i]);
    }

    for (size_t i=0; i<N; i++) {
        int result[1000][1000] = {0, };
        // printf("WORD: %s\n", WORD[i]);
        for (size_t j=0; j<N; j++) {
            if (i != j) {
                process(i, j, result[j]);
                // printf("    OTHER: %s    ", WORD[j]);
                // for (int x=0; x<strlen(WORD[i]); x++) {
                //     printf("%d ", result[j][x]);
                // }
                // printf("\n");
            }
        }
        find_shortest(i, result);
    }
}