// HACKERRANK: Insertion Sort Advanced Analysis
// https://www.hackerrank.com/challenges/insertion-sort
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

vector<int> merge(vector<int> left, vector<int> right, long long &count) {
	vector<int> result;

	auto il = left.begin();
	auto ir = right.begin();
	size_t inv = left.size();

	while (il!=left.end() || ir!=right.end()) {
		if (il==left.end()) {
			result.push_back(*ir);
			ir++;
		} else if (ir==right.end()) {
			result.push_back(*il);
			il++;
			inv--;
		} else if (*il > *ir) {
			count+=inv;
			result.push_back(*ir);
			ir++;
		} else {
			result.push_back(*il);
			il++;
			inv--;
		}
	}

	return result;
}

vector<int> merge_sort(vector<int> const &A, long long &count) {
	if (A.size() == 1)
		return A;
	auto mid = A.begin() + (A.size() / 2);

	vector<int> left{A.begin(), mid};
	vector<int> right{mid, A.end()};

	left = merge_sort(left, count);
	right = merge_sort(right, count);

	return merge(left, right, count);
}

int main() {
	int T;

	cin >> T;
	while (T--) {
		int N;
		long long count = 0;
		vector<int> A;

		cin >> N;
		while (N--) {
			int a;
			cin >> a;
			A.push_back(a);
		}
		A = merge_sort(A, count);
		cout << count << endl;
	}
    return 0;
}
