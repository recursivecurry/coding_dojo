// CODECHEF - Farmer Feb
// http://www.codechef.com/APRIL14/problems/POTATOES/
#include <cstdio>
#include <array>
#include <map>
#include <cmath>

int main (int argc, char *argv[]) {

	int T;
	int P[1000][2] = {0, };
	int max=0;

	scanf("%d", &T);

	for (int i=0; i<T; i++) {
		scanf("%d %d", &P[i][0], &P[i][1]);
		if (max < (P[i][0]+P[i][1]))
			max = P[i][0] + P[i][1];
	}

	int prime[2004] = {0, };
	prime[0] = 1;
	prime[1] = 1;

	for (int i=2;i<2004; i++) {
		if (!prime[i]) {
			for (int j=2; i*j<2004; j++) {
				prime[i*j] = 1;
			}
		}
	}

	for (int i=0; i<T; i++) {
		int j=0;
		int sum = P[i][0] + P[i][1];

		for (j=sum+1; j<2004 && prime[j]; j++);
		printf("%d\n", j-sum);
	}
}
