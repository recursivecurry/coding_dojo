/*
 * HEADER
 */
#include <cstdio>
#include <cstdlib>
#include <cstring>

#include <iostream>

#include <array>
#include <deque>
#include <forward_list>
#include <list>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <unordered_map>
#include <unordered_set>
#include <vector>

#include <algorithm>
#include <bitset>
#include <functional>
#include <limits>
#include <numeric>
#include <regex>
#include <string>
#include <utility>


using namespace std;

/*
 * PRE-DEFINED FUNCTION
 */

// Power function for Integer
long long POWI(long long base, long long exp) {
    long long result = 1;

    while (exp) {
        if (exp & 1)
            result *= base;
        exp >>= 1;
        base *= base;
    }

    return result;
}

// Factorial


int main (int argc, char *argv[]) {
    cout << POWI(2,0) << ", " << POWI(2,1) << ", " << POWI(2, 10) << endl;
    cout << POWI(3,0) << ", " << POWI(3,1) << ", " << POWI(3, 10) << endl;
}

