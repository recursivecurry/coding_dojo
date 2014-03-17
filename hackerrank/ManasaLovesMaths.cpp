// HACKERRANK - Manasa Loves Maths
// https://www.hackerrank.com/contests/mar14/challenges/manasa-loves-maths
#include <iostream>
#include <algorithm>
#include <string>
#include <set>

using namespace std;

string one[2] = {"0", "8"};
string two[11] = {"04", "08", "16", "23", "24", "27", "46", "48", "56", "69", "88"};
string three[95] = {"000", "002", "004", "006", "008", "012", "014", "016", "023", "024", "025", "027", "028", "029", "034", "036", "044", "045", "046", "047", "048", "049", "056", "067", "068", "069", "088", "112", "123", "125", "126", "127", "128", "129", "136", "144", "146", "148", "166", "167", "168", "223", "224", "227", "234", "235", "236", "238", "239", "244", "246", "247", "248", "255", "256", "257", "258", "259", "267", "269", "278", "279", "288", "289", "299", "336", "344", "348", "356", "367", "368", "369", "445", "446", "447", "448", "449", "456", "458", "466", "468", "469", "478", "488", "489", "566", "567", "568", "669", "677", "678", "679", "688", "689", "888"};

bool check_three (string in) {

	int p1=0, p2=0;

	for (int i=0; i<95; i++) {
		string::iterator it1 = in.begin();
		string::iterator it2 = three[i].begin();

		while (it1 != in.end() && it2 != three[i].end()) {
			if (*it1 == *it2) {
				it1++;
				it2++;
			} else if (*it1 < *it2) {
				it1++;
			} else {
				break;
			}
		}
		if (it2 == three[i].end())
			return true;
	}
	return false;
}

int main() {
	int T;
	string input;

	cin >> T;

	while (T--) {
		set<string>::iterator it;

		cin >> input;
		sort(input.begin(), input.end());

		if (1==input.length()) {
			set<string> one_set(one, one+2);
			it = one_set.find(input);
			if (one_set.end() != it)
				cout << "YES" << endl;
			else
				cout << "NO" << endl;
		} else if (2==input.length()) {
			set<string> two_set(two, two+11);
			it = two_set.find(input);
			if (two_set.end() != it)
				cout << "YES" << endl;
			else
				cout << "NO" << endl;
		} else {
			if (check_three(input))
				cout << "YES" << endl;
			else
				cout << "NO" << endl;

		}
	}
    return 0;
}