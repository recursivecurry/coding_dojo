// CODECHEF: Chef and Ground
// http://www.codechef.com/OCT14/problems/CHEFGR
#include <iostream>

using namespace std;


int main (int argc, char *argv[]) {
	int T;

	cin >> T;
	
	while (T--) {
		int N, M;
		int max = 0;

		cin >> N >> M;

		for (int idx=0; idx<N; idx++) {
			int height;

			cin >> height;

			if (max < height) {
				M -= ((height - max) * idx);
				max = height;
			} else {
				M -= (max-height);
			}
		}
		if (M % N)
			cout << "No" << endl;
		else
			cout << "Yes" << endl;
	}
}