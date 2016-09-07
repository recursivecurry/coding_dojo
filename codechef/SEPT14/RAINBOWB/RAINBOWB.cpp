#include <cstdio>
#include <vector>
#include <iostream>

using namespace std;

int main (int argc, char *argv[]) {

	long long N, R;
	long long result, start, current;

	scanf("%lld", &N);

	if ( N < 13 ) {
		result = 0;
	}
	else {
		vector<long long> div_set;

		result = 1;
		R = ((N+1)/2) - 7;
		start = 7 + R - 1;

		if (6 < R)
			R = 6;
		for (int idx=1; idx<R; idx++) {
			if (idx+1 == 4) {
				div_set.push_back(2);
				div_set.push_back(2);
			} else if (idx+1 == 6) {
				div_set.push_back(2);
				div_set.push_back(3);
			} else
				div_set.push_back(idx+1);
		}

		for (int idx=0; idx<R; idx++) {
			vector<long long> temp;
			current = start--;
			for (auto it : div_set) {
				if (0 == current % it) {
					current /= it;
				}
				else {
					temp.push_back(it);
					it++;
				}
			}
			div_set.swap(temp);
			result *= current;
			result %= 1000000007;
		}
	}

	printf("%lld\n", result);

	return 0;
}
