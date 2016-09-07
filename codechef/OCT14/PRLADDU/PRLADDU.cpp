// CODECHEF: DevuLand, Dinosaurs and Laddus
// http://www.codechef.com/OCT14/problems/PRLADDU
#include <cstdio>
#include <cstdlib>

using namespace std;


int main(int argc, char *argv[]) {
	int T;

	scanf("%d", &T);

	while (T--) {
		long long N;
		long long prev = 0;
		long long sum = 0;

		scanf("%lld", &N);

		scanf("%lld", &prev);
		N--;

		while (N--) {
			long long val;

			scanf("%lld", &val);

			sum += abs(prev);
			prev += val;
		}

		printf("%lld\n", sum);
	}
	return 0;
}