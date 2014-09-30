// HACKERRANK: Almost sorted interval
// https://www.hackerrank.com/challenges/almost-sorted-interval
#include <iostream>
#include <cstdio>
#include <map>
#include <utility>

using namespace std;

int main(int argc, char *argv[]) {
	int N, n, prev;
	long long c;
	map<int, int> smap;

	cin >> N;
	c = N;

	scanf("%d", &prev);
	N--;
	while (N--) {
		scanf("%d", &n);
		if (prev < n) {
			smap[prev] = n;

			auto it = smap.begin();
			while (it != smap.end() && it->first <= prev) {
				it++;
			}

			smap.erase(it, smap.end());
		}

		for (auto s : smap) {
			if (s.second<=n) {
				smap[s.first] = n;
				c++;
			}
		}
		prev = n;
	}

	cout << c << endl;
}