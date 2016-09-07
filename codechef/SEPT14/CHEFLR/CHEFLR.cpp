// CODECHEF: Chef and Left-Right
// http://www.codechef.com/SEPT14/problems/CHEFLR
#include <iostream>
#include <vector>
#include <queue>

using namespace std;


long long solve (string const &path) {

	long long answer;
	long long pos =  0;
	long long level = path.length()+1;
	long long prev = 0, sum=0;

	for (auto i= 1; i<level; i++) {

		if (i%2 == level%2) {
			switch (i) {
				case 1:
					prev = 1;
					break;
				case 2:
					prev = 2;
					break;
				default:
					prev *= 4;
			}
			prev %= 1000000007;
			sum += prev;
			sum %= 1000000007;
		}

		pos *= 2;
		switch (path[i-1]) {
			case 'l':
				pos += 0;
				break;
			default:
				pos += 1;
				break;
		}
		pos %= 1000000007;
	}

	if (level%2 == 0) {
		answer = 2 + (sum+pos)*2;
	} else {
		answer = 1 + (sum+pos)*2;
	}

	return answer % 1000000007;
}

int main (int argc, char *argv[]) {

//	int T;

//	cin >> T;

//	while (T--) {
//		string path;
//		cin >> path;
//		cout << solve(path) << endl;
//	}

	queue<string> item;
	item.push("l");
	item.push("r");
	long long count = 0;
	while (0 < item.size()) {
		string cur = item.front();
		item.pop();
		cout << "{" << cur << ", " << solve(cur) << "},";
		if (cur.length() < 100000) {
			item.push(cur.append("l"));
			item.push(cur.append("r"));
		}
		count++;
		if (count % 10000 == 0)
			cerr << count << endl;
	}
	return 0;
}