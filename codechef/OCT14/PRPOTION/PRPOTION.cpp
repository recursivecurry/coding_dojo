// CODECHEF Magical Girl and Colored Liquid Potions
// http://www.codechef.com/OCT14/problems/PRPOTION/
#include <cstdio>

using namespace std;


int main(int argc, char *argv[]) {
	int T;

	scanf("%d", &T);

	while (T--) {
		int R, G, B, M;
		long long r=0, g=0, b=0;

		scanf("%d %d %d %d", &R, &G, &B, &M);

		while (R--) {
			long long val;
			scanf("%lld", &val);
			if (r < val)
				r = val;
		}
		while (G--) {
			long long val;
			scanf("%lld", &val);
			if (g < val)
				g = val;
		}
		while (B--) {
			long long val;
			scanf("%lld", &val);
			if (b < val)
				b = val;
		}

		//printf ("R: %lld G: %lld B: %lld\n", r, g, b);
		long long max = 1000000000;
		M++;
		while (M--) {
			if (g <= r && b <= r) {
				if (r < max)
					max = r;
				r /= 2;
			} else if (r <= g && b <= g) {
				if (g < max)
					max = g;
				g /= 2;
			} else if (r <= b && g <= b) {
				if (b < max)
					max = b;
				b /= 2;
			}
		}
		printf("%lld\n", max);
	}
}