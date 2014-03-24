// HACKERRANK - Ones and Twos
// https://www.hackerrank.com/contests/mar14/challenges/ones-and-twos

#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

static bool DEBUG = false;

void blank(long long depth) {
	for(long long idx=0; idx<depth; idx++)
		printf("    ");
}

long long count_numbers(long long ord, long long two, long long one, long long sum, long long next, long long sel, long long depth) {

	if (DEBUG) { blank(depth); printf("%lld-%lld(%lld) TWO:%lld ONE:%lld SUM:%lld NEXT:%lld\n", depth, ord, sel, two, one, sum, next); }

	if (two < 0) {
		if (DEBUG) { blank(depth); printf("X => %lld\n", -1LL); }
		return -1;
	}

	if (next <= (sum+one+1)) {
		if (DEBUG) { blank(depth); printf("A=> %lld\n", 0LL); }
		return 0;
	}

	if (0==two || 0==ord) {
		if (DEBUG) { blank(depth); printf("B=> %lld\n", (next - sum - one - 1LL)); }
		return (next - sum - one - 1LL);
	} else  {
		long long ret;
	    ret = count_numbers(ord-1, two-ord, one, sum+(1LL<<ord), next, 1LL, depth+1);
	    if (-1 == ret) {
	    	ret = count_numbers(ord-1, two, one, sum, next, 0LL, depth+1);
	    } else if (0 == ret) {
	    	ret = 0;
	    } else {
	    	ret += count_numbers(ord-1, two, one, sum, sum+(1LL<<ord), 0LL, depth+1);
	    }
		if (DEBUG) { blank(depth); printf("*=> %lld\n", ret); }
		return ret;
	}
}

int main() {

	long long t, one, two;

	cin >> t;

	while (t--) {
		long long count=0, exclude=0;

		cin >> one >> two;

		count = ((1LL<<two) + one) - count_numbers(two-1, two, one, 0, (1LL<<two), 0, 0);
		cout << count << endl;
	}

    return 0;
}
