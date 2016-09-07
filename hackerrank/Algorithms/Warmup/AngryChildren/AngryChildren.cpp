// HACKERRANK: Angry Children
// https://www.hackerrank.com/challenges/angry-children
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int solve (vector<int> &xs, int k) {

    int unfairness;

    sort(xs.begin(), xs.end());
    unfairness = xs.back();

    for (int pos=k; pos<xs.size(); pos++) {
        int localUnfairness = xs[pos] - xs[pos-k];
        if (localUnfairness < unfairness)
            unfairness = localUnfairness;
    }

    return unfairness;
}

int main (int argc, char *argv[]) {
    int N, K;
    vector<int> XS;

    cin >> N >> K;

    while (N--) {
        int val;
        cin >> val;
        XS.push_back(val);
    }

    cout << solve(XS, K-1) << endl;
    return 0;
}