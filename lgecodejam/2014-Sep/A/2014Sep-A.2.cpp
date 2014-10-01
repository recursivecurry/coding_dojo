#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main(int argc, char *argv[]) {
	int T;

	cin >> T;

	while (T--) {
		string digit{"123456789ABCDEF"};
		size_t pos = 0;
		string cmps;
		vector<char> stack;

		cin >> cmps;

		for (char c : cmps) {
			char d = digit[pos++];
			if (c == '<') {
				while (stack.empty() == false) {
					cout << stack.back() << " ";
					stack.pop_back();
				}
				cout << d << " ";
			} else {
				stack.push_back(d);
			}
		}
		stack.push_back(digit[pos]);
		while (stack.empty() == false) {
			cout << stack.back() << " ";
			stack.pop_back();
		}
		cout << endl;
	}
}