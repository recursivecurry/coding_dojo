// HACKERRANK - Utopian Tree
// https://www.hackerrank.com/challenges/utopian-tree
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


int main() {
    int t, n;
    scanf("%d", &t);
    while(t--)
    {
        int h=1;
        scanf("%d", &n);
        for (int i=1; i<=n; i++)
        {
            if (0 == i%2)
                h++;
            else
                h*=2;
        }
        printf("%d\n", h);
    }
        return 0;
}
