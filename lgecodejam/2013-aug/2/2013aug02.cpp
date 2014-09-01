#include <iostream>
#include <vector>

using namespace std;

vector<int> input[2];
vector<bool> status[2];
bool result[2];


bool analyze(int day) {

	int from=1, to=2;
	for (int idx=0; idx<input[day].size(); idx++) {
		if (input[day][idx] == from) {
			status[day].push_back(false);
			to = 7 - from - to;
		}
		else if (input[day][idx] == to) {
			status[day].push_back(true);
			from = 7 - from - to;
		}
		else
			break;
	}

	if (status[day].size() == input[day].size())
		return true;
	else
		return false;
}

int main (int argc, char * argv[]) {

	int T;

	cin >> T;

	while (T--) {

		input[0].clear();
		input[1].clear();
		status[0].clear();
		status[1].clear();

		int N;

		cin >> N;

		for (int idx=0; idx<N; idx++) {
			int val;
			cin >> val;
			input[0].push_back(val==3?4:val);
		}

		for (int idx=0; idx<N; idx++) {
			int val;
			cin >> val;
			input[1].push_back(val==3?4:val);
		}

		result[0] = analyze(0);
		result[1] = analyze(1);

		if (result[0]
			&& result[1]
			&& (status[0] <= status[1]))
			cout << "1" << endl;
		else
			cout << "0" << endl;




	}
}