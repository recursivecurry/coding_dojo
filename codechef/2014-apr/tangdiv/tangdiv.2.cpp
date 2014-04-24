#include <cstdio>
#include <cstring>

using namespace std;

unsigned char KS[50000000] = {0, };

int main(int argc, char *argv[]) {
	int T;

	scanf("%d", &T);
	while (T--) {
		int N, K, P, T2=T+1;
		bool answer = true;
		scanf("%d %d %d", &N, &K, &P);
		while (K--) {
			int l, r;
			scanf("%d %d", &l, &r);
			KS[l-1] = T2;
		}
		while (P--) {
			int a, b;
			scanf("%d %d", &a, &b);
			if ( answer && T2!=KS[a-1]) {
				answer = false;
				break;
			}
		}
		while (!answer && P--) {
			int a, b;
			scanf("%d %d", &a, &b);
		}
		if (answer)
			printf("Yes\n");
		else
			printf("No\n");
	}
	return 0;
}