// HACKERRANK: Sherlock and The Beast
// https://www.hackerrank.com/challenges/sherlock-and-the-beast
#include <iostream>
#include <string>

using namespace std;

string solve (int five, int three) {
    string result{""};

    while (true) {
        if (five < 0)
            return string("-1");
        else if (five % 3 == 0 && three % 5 == 0)
            break;
        else {
            five -= 5;
            three += 5;
        }
    }

    if (five < 0)
        return "-1";

    while (five--) {
        result.append("5");
    }

    while (three--) {
        result.append("3");
    }

    return result;
}

int main (int argc, char *argv[]) {
    int T;

    cin >> T;

    while (T--) {
        int N;
        cin >> N;
        cout << solve(N, 0) << endl;
    }

}