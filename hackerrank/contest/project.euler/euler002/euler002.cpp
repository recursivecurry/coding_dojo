#include <iostream>
#include <cstdio>
#include <utility>
#include <vector>

using namespace std;

pair<long long, long long> fibo(pair<long long, long long> prev);

pair<long long, long long> fibo(pair<long long, long long> prev) {
    return pair<long long, long long>{prev.first+prev.second, prev.first+prev.second+prev.second};
}


int main() {
    int t;
    scanf("%d", &t);

    vector<long long> evens;
    pair<long long, long long> fibo = {1, 2};
    while (fibo.first <= 40000000000000000) {
        if (fibo.first % 2 == 0) evens.push_back(fibo.first);
        else if (fibo.second % 2 == 0) evens.push_back(fibo.second);
        fibo.first = fibo.first + fibo.second;
        fibo.second = fibo.first + fibo.second;
    }
    for (int i=0; i<t; i++) {
        long long n=0, r=0;

        scanf("%lld", &n);
        for (long long v : evens) {
           if (v <= n)
               r += v;
        }
        printf("%lld", r);
    }
}