// HACKERRANK - Counting Sort 3
// https://www.hackerrank.com/challenges/countingsort3
#include <iostream>
#include <string>

using namespace std;

int number[100]{0, };

class Input {
public:
    int num;
    string text;
    Input(int num, string text) {
        this->num = num;
        this->text = text;
    }
};

int main(int argc, char *argv[]) {
    int N;
    int num;
    int idx, sum=0;
    string text;

    cin >> N;

    while (N--) {
        cin >> num >> text;
        number[num]++;
    }

    for (int idx=0; idx<100; idx++) {
        sum += number[idx];
        cout << sum << " ";
    }

    return 0;
}