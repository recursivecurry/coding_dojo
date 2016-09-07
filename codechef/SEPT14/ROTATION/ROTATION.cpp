#include <iostream>
#include <vector>
#include <utility>

using namespace std;


int main (int argc, char *argv[]) {
	int N, M;
	vector<int> A;
	int pos=0;

	cin >> N >> M;

	for (int idx=0; idx<N; idx++) {
		int val;
		cin >> val;
		A.push_back(val);
	}

	while (M--) {
		char c;
		int val;
		cin >> c >> val;
		switch (c) {
			case 'A':
				val = N - val;
			case 'C':
				pos = (pos +  val) % N;
				break;
			case 'R':
				cout << A[(pos+val-1)%N] << endl;
				break;
		}
	}

	return 0;
}