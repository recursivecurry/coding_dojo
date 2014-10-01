#include <iostream>
#include <vector>

using namespace std;

int main(int argc, char *argv[]) {
	int T;

	cin >> T;

	while (T--) {
		long long N;
		int L;

		cin >> N >> L;

		long long s=1, e=N, prev;
		int a;
		long long b, m;

		cin >> prev >> a;
		L--;

		while (L--) {

			cin >> b >> a;

			if (0==a) {	// 이전보다 가깝다
				if (prev < b) {
					m = (prev + b + 2) / 2;
					//cerr << "1: " << prev << " " << b << " " << m << endl;
					if (s <= m && m <= e)
						s = m;
				} else {
					m = (prev + b - 1) / 2;
					//cerr << "2: " << prev << " " << b << " " << m << endl;
					if (s <= m && m <= e)
						e = m;
				}
			} else if (1==a) {	// 이전보다 멀다
				if (prev < b) {
					m = (prev + b - 1) / 2;
					//cerr << "3: " << prev << " " << b << " " << m << endl;
					if (s <= m && m <= e)
						e = m;
				} else {
					m = (prev + b + 2) / 2;
					//cerr << "4: " << prev << " " << b << " " << m << endl;
					if (s <= m && m <= e)
						s = m;
				}
			} else if (2==a) {	// 거리가 같다.
				if (prev != b) {
					s = (prev + b) / 2;
					e = s;
				}
			}

			//cerr << "A("<<s<<" : " << e << ") " << ((e - s)+1) << endl;
			cout << ((e - s) + 1) << " ";
			prev = b;
		}
		cout << endl;
	}

	return 0;
}