// HACKERRANK - Ones and Twos
// https://www.hackerrank.com/contests/mar14/challenges/ones-and-twos

#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


int main() {

	long long t, one, two;

	cin >> t;

	while (t--) {
		long long count=0, exclude=0;

		cin >> one >> two;

		if (0==one && two==0)
			count = 0;
		else if (0==one)
			count = 1<<(two-1);
		else if (0==two)
			count = one;
		else {
			count = (1<<two)+one;
//			cout << count << endl;
			for (long long idx=1; idx<(two-1); idx++) {
//				cout << (1<<idx) << " " << one << " " << (1<<idx)-one-1 << endl;
				exclude+=(1<<idx)- one - 1;
			}
		}

		count-=exclude;

		cout << count << endl;
	}

    return 0;
}