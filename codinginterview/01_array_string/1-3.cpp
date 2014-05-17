#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

bool isPermutation(string &s1, string &s2) {
    bool flag = false;
    string ss, ls;

    if (s1.length() < s2.length()) {
        ss = s1;
        ls = s2;
    } else {
        ss = s2;
        ls = s1;
    }

    sort(ls.begin(), ls.end());
    sort(ss.begin(), ss.end());

    auto itl = ls.begin();
    auto its = ss.begin();

    while (its != ss.end()) {
        if (itl == ls.end()) {
            flag = false;
            break;
        }
        if (*its == *itl) {
            its++;
        }
        itl++;
    }

    return flag;
}

int main (int argc, char *argv[]) {
    string s1{argv[1]};
    string s2{argv[2]};

    cout << (isPermutation(s1, s2) ? "True" : "False") << endl;


    return 0;
}