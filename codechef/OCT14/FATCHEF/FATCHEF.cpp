// CODECHEF: Remy paints the fence
// http://www.codechef.com/OCT14/problems/FATCHEF/
#include <cstdio>
#include <map>
#include <array>

using namespace std;


int main(int argc, char *argv[]) {
	int T;

	scanf("%d", &T);

	while (T--) {
		int N, M;
		array<char, 100001> plank{0, };
		long long ans=1;

		scanf ("%d %d", &N, &M);
		while (M--) {
			char c;
			int b;
			scanf(" %c %d", &c, &b);
			plank[b] = c;
		}

		int prev_pos = 0, pos = 1;
		while (plank[pos] == 0) {
			pos++;
		}
		prev_pos = pos;
		for (; pos <=N; pos++) {
			if (plank[pos] != 0) {
				if (plank[prev_pos] != plank[pos]) {
					int diff = pos - prev_pos;
					ans *= (long long)diff;
					ans %= 1000000009LL;
				}
				prev_pos = pos;
			}
		}
		printf("%lld\n", ans);
	}
	return 0;
}