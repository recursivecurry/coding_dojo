#include <iostream>
#include <cstdio>
#include <string>
#include <set>
#include <set>

using namespace std;

bool solve_iter(string cmps, string digit, string ans) {

	//cerr << "ANS: " << ans << endl;

	if (cmps.empty()) {
		cout << ans << endl;
		return true;
	}

	bool ret = false;
	char c = cmps[0];
	string cs = cmps.substr(1);

	for (int idx=0; idx<digit.size(); idx++) {
		char d = digit[idx];

		//cerr << "D: " << d << endl;

		if (c=='>' && ans.back() < d)
			continue;
		else if (c=='<' && ans.back() > d)
			continue;
		string ds(digit);
		ds.erase(idx,1);
		string newans(ans);
		newans.push_back(d);

		if (solve_iter(cs, ds, newans)) {
			ret = true;
			break;
		}
	}
	return ret;
}

void solve (string cmps) {
	string digit{"123456789ABCDEF"};

	for (int idx=0; idx<digit.size(); idx++) {
		string ans{""};
		ans.push_back(digit[idx]);

		string ds(digit);
		ds.erase(idx, 1);

		//cerr << ds << endl;
		if (solve_iter(cmps, ds, ans))
			break;
	}
}

int main (int argc, char *argv[]) {
	int T;

	cin >> T;

	while (T--) {
		string cmps;

		cin >> cmps;

		solve(cmps);
	}
	return 0;
}