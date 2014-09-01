#include <iostream>
#include <array>
#include <vector>
#include <map>
#include <set>
#include <unordered_map>
#include <unordered_set>
#include <cstdio>

using namespace std;

const int DEBUG = 0;

const int MAX_N = 1000;
const int MAX_K = 10;


int N, M, K, L;
unordered_map<int, unordered_map<int, int>> path;
array<long long, MAX_K+2> result;
const array<int, MAX_K+2> mod{0, 0, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

const long long MOD = 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23 * 29;

void add_path(int from , int to) {

	path[to][from]++;
}

void solve() {
	unordered_map<int, long long> mid{{1, 1}};
	if (1 < DEBUG) {
		cerr << "NEW" << endl;
		for (auto n : mid) {
			cerr << n.first << "(" << n.second << ") ";
		}
		cerr << endl;
	}
	for (int level=0; level<L; level++) {
		unordered_map<int, long long> temp{};
		if (0<DEBUG) {
			cerr << " level: " << level << " size: " << mid.size() << endl;
		}

		for (auto from : mid) {
			if (0 < from.second) {
				for (auto next : path[from.first]) {
					temp[next.first] += (mid[from.first] * next.second);
					temp[next.first] %= MOD;
				}
			}
		}
		if (1 < DEBUG) {
			cerr << "TEMP" << endl;
			for (auto t : temp) {
				cerr << t.first << "(" << t.second << ") ";
			}
			cerr << endl;
		}
		for (int idx=2; idx<=K+1; idx++) {
			result[idx] += temp[idx];
			result[idx] %= mod[idx];
		}
		mid.swap(temp);
	}
}

int main (int argc, char *argv[]) {

	int T;

	scanf("%d", &T);
	//cin >> T;
	while (T--) {
		if (0 < DEBUG)
			cerr << "T: " << T << endl;

		int val, count;

		path.clear();
		result.fill(0);


		scanf("%d %d %d %d", &N, &M, &K, &L);
		//cin >> N >> M >> K >> L;

		if (0 < DEBUG)
			cerr << "N: " << N << " M: " << M << " K: " << K << " L: " << L << endl;

		for (int idx=0; idx<M; idx++) {

			int val, val2;

			//cin >> val >> val2;
			scanf("%d %d", &val, &val2);
			add_path(val, val2);
		}

		if (1 < DEBUG) {
			for ( auto to=path.begin(); to!=path.end(); to++) {
				for (auto num=to->second.begin(); num!=to->second.end(); num++) {
					cerr << to->first << " -> " << num->first << " : " << num->second << endl;
				}
			}
		}

		solve();

		if (0 < DEBUG) {
			for (int idx=2; idx<K+2; idx++) {
				if (idx==0)
					cerr << result[idx];
				else
					cerr << " " << result[idx];
			}
			cerr << endl;
		}
		for (int idx=2; idx<K+2; idx++) {
			if (idx==0)
				cout << (result[idx] % mod[idx]);
			else
				cout << " " << (result[idx] % mod[idx]);
		}
		cout << endl;
	}
}