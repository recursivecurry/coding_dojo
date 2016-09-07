// HACKERRANK - Gem Stones
// https://www.hackerrank.com/challenges/gem-stones

#include <iostream>
#include <string>

using namespace std;

int main (int argc, char *argv[]) {
	int N, N2, R;
	string input{};
	size_t result[26]{0, };

	scanf("%d", &N);

	N2 = N;

	while (N2--) {
		size_t current[26]{0, };

		cin >> input;

		for (string::iterator it = input.begin(); it != input.end(); it++) {
			current[*it-'a'] = 1;
		}
		for (size_t idx=0; idx<26; idx++) {
			result[idx] += current[idx];
		}
	}

	for (size_t idx=0; idx<26; idx++) {
		if (result[idx] == N)
			R++;
	}

	cout << R << endl;
}
