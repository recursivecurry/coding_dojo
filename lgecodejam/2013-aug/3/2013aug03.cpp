#include <iostream>
#include <array>
#include <vector>
#include <map>
#include <set>
#include <unordered_map>
#include <unordered_set>

using namespace std;

const int MAX_N = 1000;
const int MAX_K = 10;

unordered_map<int, unordered_map<int, int>> path;
array<long long, MAX_K> result;
array<int, 10> mod{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

void add_path(int from , int to) {
	path[to][from]++;
}

int get_path(int from, int to) {
	return path[to][from];
}

int main (int argc, char *argv[]) {

	int T;

	cin >> T;
	while (T--) {
		cout << "T: " << T << endl;

		int N, M, K, L;
		int val, count;

		path.clear();
		result.fill(0);

		cin >> N >> M >> K >> L;

		for (int idx=0; idx<M; idx++) {

			int val, val2;

			cin >> val >> val2;
			add_path(val, val2);
		}

		for ( auto to=path.begin(); to!=path.end(); to++) {
			for (auto num=to->second.begin(); num!=to->second.end(); num++) {
				cout << to->first << " -> " << num->first << " : " << num->second << endl;
			}
		}
	}
}