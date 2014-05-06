#include <iostream>
#include <string>

using namespace std;

int main (int argc, char *argv[]) {
    int T;

    cin >> T;
    while (T--) {
        size_t left = 0;
        size_t right = 0;
        size_t max = 0;
        size_t prev = 0;
        string input;

        cin >> input;

        for (auto pos : input) {

            if (pos == '<') {
                if (right == 0) {
                    left++;
                } else {
                    if (left == 0)
                        prev = prev + right * 2;
                    else
                        prev = right * 2;
                    left = 1;
                    right = 0;
                }
            } else if (pos == '>') {
                if (left > 0) {
                    left--;
                    right++;
                } else {
                    if (right > 0) {
                        prev = prev + right * 2;
                        if (max < prev)
                            max = prev;
                        right = 0;
                        prev = 0;
                        break;
                    }
                }
            }
        }
        prev = prev + right * 2;
        if (max < prev)
            max = prev;
        cout << max << endl;
    }
}