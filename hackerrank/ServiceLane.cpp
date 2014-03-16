// HACKERRANK - Service Lane
// https://www.hackerrank.com/challenges/service-lane
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


int main() {
    int N, T;
    int ND[100000] = {0, };
    scanf("%d", &N);
    scanf("%d", &T);
    for (int i=0; i<N; i++)
    {
        scanf("%d", &ND[i]);
    }
    while (T--)
    {
        int i, j, min=4;
        scanf("%d", &i);
        scanf("%d", &j);
        for(;i<=j;i++)
        {
            if (ND[i]<min)
            {
                min = ND[i];
            }
        }
        printf("%d\n", min);
    }
        return 0;
}
