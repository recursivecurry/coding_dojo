#include <iostream>
#include <string>

using namespace std;

const static string ONE[] = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve"};
const static string TEN[] = {"eleven", "twelve", "thirteen", "fourteen", "quarter", "sixteen", "seventeen", "eighteen", "nineteen"};
const static string TENS[] = {"zero", "ten", "twenty", "half"};

string minutes(int n) {
    string pastto;
    if (n <= 30)
        pastto = " past ";
    else {
        n = 60 - n;
        pastto = " to ";
    }

    string minute;
    if (n == 1)
        minute = " minute";
    else if (n % 15 == 0)
        minute = "";
    else
        minute = " minutes";

    string number;
    int ten = n / 10, one = n % 10;

    if (n<10)
        number = ONE[n];
    else if (one == 0) {
        number = TENS[n/10];
    } else if (10 < n && n < 20) {
        number = TEN[n-11];
    } else {
        number = TENS[ten] + ((one == 0) ? "" : " " + ONE[one]);
    }
    return number + minute + pastto;
}

int main() {
	int h, m;

    cin >> h >> m;

    if (m == 0) {
        cout << ONE[h] << " o' clock";
    } else {
        if (m<=30)
            cout << minutes(m) << ONE[h];
        else
            cout << minutes(m) << ONE[h+1];
    }
}