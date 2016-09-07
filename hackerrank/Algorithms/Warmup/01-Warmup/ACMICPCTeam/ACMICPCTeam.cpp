// HACKERRANK: ACM ICPC Team
// https://www.hackerrank.com/challenges/acm-icpc-team
#include <iostream>

#include <algorithm>
#include <functional>

#include <vector>
#include <unordered_set>

using namespace std;


vector<vector<bool>> person;

int team(vector<bool> const &a, vector<bool> const &b) {
	int count = 0;
	for (size_t idx=0; idx < a.size(); idx++) {
		if (a[idx] || b[idx])
			count++;
	}
	return count;
}

int main (int argc, char *argv[]) {
	int N, M;

	cin >> N >> M;

	for (size_t idx=0; idx<N; idx++) {

		string line;
		vector<bool> topic;

		topic.resize(M);

		cin >> line;

		transform(line.begin(), line.end(), topic.begin(), [] (char c) -> bool { return c - '0';});
		person.push_back(topic);
	}

	int max = 0;
	unordered_multiset<int> result;
	for (size_t x=0; x<N; x++) {
		for (size_t y=x+1; y<N; y++) {
			int value = team(person[x], person[y]);
			result.insert(value);
			if ( max < value )
				max = value;
		}
	}
	cout << max << endl;
	cout << result.count(max) << endl;
}