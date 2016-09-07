#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


int main() {
    int T;
    
    cin >> T;
    
    while (T--) {
        int M, N;
        int m = 1, n=1;
        long long input, output=0;
        vector<long long> MS, NS;
        
        cin >> M >> N;
        M--;
        N--;
        while (M--) {
            cin >> input;
            MS.push_back(input);
        }
        while (N--) {
            cin >> input;
            NS.push_back(input);
        }
        sort(MS.begin(), MS.end(), [] (long long x, long long y) {return x>y;});
        sort(NS.begin(), NS.end(), [] (long long x, long long y) {return x>y;});
        vector<long long>::iterator mi = MS.begin(), ni = NS.begin();
        while (true) {
            if (mi == MS.end() && ni == NS.end()) {
                //cerr << "<END>" << endl;
                output %= 1000000007;
                break;
            }
            else if (mi == MS.end()) {
                output += ((*ni * m) % 1000000007);
                //cerr << "<ONLY N> ni:" << *ni << ", m:" << m << ", output:" << output << endl;
                ni++;
                n++;
            }
            else if (ni == NS.end()) {
                output += ((*mi * n) % 1000000007);
                //cerr << "<ONLY M> *mi:" << *mi << ", n:" << n << ", output:" << output << endl;
                mi++;
                m++;
            }
            else if (*mi >= *ni) {
                //cerr << "<M>=N> *mi:" << *mi << ", *ni:" << *ni << "n:" << n << ", output:" << output << endl;
                output += ((*mi * n) % 1000000007);
                mi++;
                m++;
            }
            else {
                //cerr << "<M<N> *mi:" << *mi << ", *ni:" << *ni << ", m:" << m << ", output:" << output << endl;
                output += ((*ni * m) % 1000000007);
                ni++;
                n++;
            }    
        }
        cout << output << endl;
    }
    return 0;
}
