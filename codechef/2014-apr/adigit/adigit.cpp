// CODECHEF - Chef and Digits (April Challenge 2014)
// http://www.codechef.com/APRIL14/problems/ADIGIT/
#include <cstdlib>
#include <cstdio>

int main(int argc, char *argv[]) {
	int N, M;
	char IN[100001] {0, };
	int MAP[100001][10] {0, };
	int XS[100000] {0, };

	scanf("%d %d", &N, &M);
	scanf("%s", IN);
	for (int i=0; i<M; i++) {
		scanf("%d", &XS[i]);
	}

	for (int n=0; n<N; n++) {
		MAP[n+1][0] = MAP[n][0];
		MAP[n+1][1] = MAP[n][1];
		MAP[n+1][2] = MAP[n][2];
		MAP[n+1][3] = MAP[n][3];
		MAP[n+1][4] = MAP[n][4];
		MAP[n+1][5] = MAP[n][5];
		MAP[n+1][6] = MAP[n][6];
		MAP[n+1][7] = MAP[n][7];
		MAP[n+1][8] = MAP[n][8];
		MAP[n+1][9] = MAP[n][9];
		MAP[n+1][IN[n]-'0'] += 1;
	}

	for (int m=0; m<M; m++) {
		int pos = XS[m]-1;
		int x = IN[pos]-'0';
		int sum = 0;

		for (int i=0; i<10; i++) {
			sum += (abs(x-i) * MAP[pos][i]);
		}
		printf("%d\n", sum);
	}
}