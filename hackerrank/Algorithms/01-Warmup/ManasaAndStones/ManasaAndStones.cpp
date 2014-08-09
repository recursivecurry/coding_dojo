// HACKERRANK: Manasa and Stones
// https://www.hackerrank.com/challenges/manasa-and-stones
#include <iostream>
#include <set>

using namespace std;

int main(int argc, char *argv[]) {
    int T;
    cin >> T;

    while (T--) {
        int N, A, B;
        set<int> from {0};
        cin >> N >> A >> B;
        while(--N) {
            set<int> next {};
            for (set<int>::iterator it = from.begin(); it != from.end(); it++) {
                next.insert(*it + A);
                next.insert(*it + B);
            }
            from.swap(next);
        }
        for (set<int>::iterator it = from.begin(); it != from.end(); it++)
            cout << *it << " ";
        cout << endl;
    }
}