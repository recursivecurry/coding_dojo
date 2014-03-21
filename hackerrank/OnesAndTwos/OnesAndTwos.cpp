// HACKERRANK - Ones and Twos
// https://www.hackerrank.com/contests/mar14/challenges/ones-and-twos

#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

static bool DEBUG = false;

long long count_numbers(int sel, long long depth, long long pos, long long two, long long one, long long sum, long long next) {

	if (DEBUG) {
		for(long long idx=0; idx<depth; idx++)
			printf("    ");
		printf("%lld(%d) TWO:%lld ONE:%lld SUM:%lld NEXT:%lld\n", pos, sel, two, one, sum, next);
	}

	if (0 == one) {
		return (1LL<<two)/2;
	}
	else if (1 == one) {
		return (1LL<<two)+1;
	}

	if (two < 0) {
		if (DEBUG) {
			for(long long idx=0; idx<depth; idx++)
				printf("    ");
			printf("X=> %lld\n", 0);
		}
		return 0;
	}

	if (next <= (sum+one)) {
		if (DEBUG) {
			for(long long idx=0; idx<depth; idx++)
				printf("    ");
			printf("A=> %lld\n", (next-sum));
		}
		return (next-sum);
	}

	if (0==two) {
		if (DEBUG) {
			for(long long idx=0; idx<depth; idx++)
				printf("    ");
			printf("B=> %lld\n", (one+1));
		}
		return (one+1);
	} else if (0==pos) {
		if (DEBUG) {
			for(long long idx=0; idx<depth; idx++)
				printf("    ");
			printf("B=> %lld\n", (one+1));
		}
		return one;
	} else  {
		long long ret;
	    ret = count_numbers(1, depth+1, pos-1, two-pos, one, sum+(1LL<<pos), next);
	    return ret + count_numbers(0, depth+1, pos-1, two, one, sum, (0 == ret) ? next : sum+(1LL<<pos));
		if (DEBUG) {
			for(long long idx=0; idx<depth; idx++)
				printf("    ");
			printf("*=> %lld\n", ret);
		}
		return ret;
	}
}

int main() {

	long long t, one, two;

	cin >> t;

	while (t--) {
		long long count=0, exclude=0;

		cin >> one >> two;

		count = count_numbers(0, 0, two, two, one, 0, (1LL<<two)+one+1);
		cout << count << endl;
	}

    return 0;
}