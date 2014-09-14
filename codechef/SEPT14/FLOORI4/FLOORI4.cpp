/*
 * HEADER
 */
#include <cstdio>
#include <cstdlib>
#include <cstring>

#include <iostream>

#include <array>
#include <deque>
#include <forward_list>
#include <list>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <unordered_map>
#include <unordered_set>
#include <vector>

#include <algorithm>
#include <bitset>
#include <functional>
#include <limits>
#include <numeric>
#include <regex>
#include <string>
#include <utility>


using namespace std;

int main (int argc, char *argv[]) {
	int T;

	scanf("%d", &T);

	while (T--) {
		long long N, M;
		long long ans=0;

		scanf("%lld %lld", &N, &M);

		for (long long i=1; i<=N; i++) {
			ans += (i*i*i*i*(N/i));
		}
		ans %= M;
		printf("== %lld\n", ans);

		for (long long i=1; i<=N; i++) {
			long long temp;
			temp = (i%M);
			temp *= (i%M);
			temp %= M;
			temp *= (i%M);
			temp %= M;
			temp *= (i%M);
			temp %= M;
			temp *= (N/i);
			temp %= M;
			ans += temp;
			ans %= M;
		}
		printf("== %lld\n", ans);
	}

	return 0;
}