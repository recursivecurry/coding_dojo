#include <iostream>
#include <vector>
#include <utility>

using namespace std;


void solve(vector<int> const &a, vector<pair<char,int>> const &q) {
	
}

int main (int argc, char *argv[]) {
	int N, M;
	vector<int> A;
	vector<pair<char, int> > Q;

	cin >> N >> M;

	while (N--) {
		int val;
		cin >> val;
		A.push_back(val);
	}

	while (M--) {
		char c;
		int val;
		cin >> c >> val;
		Q.push_back(pair<char, int>{c, val});
	}

	solve(A, Q);

	return 0;
}