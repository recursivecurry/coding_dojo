// HACKERRANK: Even Tree
// https://www.hackerrank.com/challenges/even-tree
#include <cmath>
#include <cstdio>
#include <vector>
#include <array>
#include <iostream>
#include <algorithm>
using namespace std;

const int MAX_N = 100;

array<vector<int>, MAX_N> tree;
array<int, MAX_N> childSize;

void update(int from) {
    for (vector<int>::iterator it=tree[from].begin(); it!=tree[from].end(); it++) {
        childSize[*it] += childSize[from];
    }
}

void seperate(int from) {
    //cout << "seperate (" << from << ") childSize[from]=" << childSize[from] << endl;
    vector<int> next{tree[from]};
    while (!next.empty()) {
        vector<int> temp;
        for (vector<int>::iterator it=next.begin(); it!=next.end(); it++) {
            //cout << "*it: " << *it << endl;
            childSize[*it] -= childSize[from];
            //cout << "next: " << *it << " childSize[next]=" << childSize[*it] << endl;
            for (vector<int>::iterator it2=tree[*it].begin(); it2!=tree[*it].end(); it2++) {
                //cout << "temp added: " << *it2 << endl;
                temp.push_back(*it2);
            }
        }
        swap(temp, next);
    }
    return;
}

int main() {
    int N, M, total=0;
    
    cin >> N >> M;
    //scanf("%d %d", &N, &M);
    fill_n(childSize.begin(), N+1, 1);
    while (M--) {
        int from, to;
        
        cin >> from >> to;
        //scanf("%d %d", &from, &to);
        if (from < to) {
            int temp = from;
            from = to;
            to = temp;
        }
        tree[from].push_back(to);
    }
    
    for (int i=N; i!=0; i--) {
        update(i);
    }
    
    for (int i=N; i!=0; i--) {
        if (0 == childSize[i]%2) {
            total += tree[i].size();
            seperate(i);
        }    
    }
    
    cout << total << endl;

    return 0;
}

