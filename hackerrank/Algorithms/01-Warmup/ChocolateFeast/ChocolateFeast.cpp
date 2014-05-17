// HACKERRANK - Chocolate Feast
// https://www.hackerrank.com/challenges/chocolate-feast
#include <iostream>

using namespace std;

int main(int argc, char *argv[]) {
    int T;

    cin >> T;

    while (T--) {
        int N, C, M;
        int count=0, wrapper=0;
        cin >> N >> C >> M;
        count = (N/C);
        wrapper = count;
        while (wrapper >= M) {
            int bonus = wrapper/M;
            count += bonus;
            wrapper = wrapper - bonus * (M-1);
        }
        cout << count << endl;
    }


}