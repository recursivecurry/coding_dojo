// HACKERRANK - Halloween Party
// https://www.hackerrank.com/challenges/halloween-party
#include <iostream>

using namespace std;

int main (int argc, char *argv[]) {

	int N;

	cin >> N;

	while (N--) {
		long long input;
		long long x, y;

		cin >> input;

		x = input / 2;
		y = input - x;

		cout << x * y << endl;
	}


	return 0;
}