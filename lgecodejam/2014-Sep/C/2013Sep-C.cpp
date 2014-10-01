#include <iostream>
#include <array>

using namespace std;

int N;
array<int, 20001> C{0, };
array<int, 20001> P{0, };

void insertion_sort() {
	int i, j ,tmp;
	for (i = 2; i <= N; i++) {
		int now = i;
		j = i;
		while (j > 0 && C[j - 1] > C[j]) {
			tmp = C[j];
			C[j] = C[j - 1];
			C[j - 1] = tmp;
			j--;
		}
		if (j < now) {
			P[now] = j;
			//cerr << "UP " << now << " " << j << " " << P[now] << endl;
		} else {
			P[now] = now;
			//cerr << "KEEP " << now << " " << j << " " << P[now] << endl;
		}
	}
	P[C[1]] = 1;
}

void reverse_array(int start, int end)
{
	int temp;
	while(start < end)
	{
		temp = C[start];   
		C[start] = C[end];
		C[end] = temp;
		start++;
		end--;
	}   
}

int main(int argc, char *argv[]) {
	int T;

	cin >> T;

	while (T--) {
		int K;
		int val;

		cin >> N;
		for (int idx=0; idx<N; idx++) {
			cin >> val;
			C[idx+1] = val;
			P[idx+1] = idx+1;
		}
		insertion_sort();
		int ans = 0;
		for (int idx=1; idx<=N; idx++) {
			cout << idx << " " << P[idx] << endl;
			ans += (idx * P[idx]);
			ans %= 10007;
		}
		cout << ans << endl;

		cin >> K;
		while (K--) {
			int s, e;

			cin >> s >> e;

			reverse_array(s, e);
			insertion_sort();
			int ans = 0;
			for (int idx=1; idx<=N; idx++) {
				ans += (idx * P[idx]);
				ans %= 10007;
			}
			cout << ans << endl;
		}
	}

	return 0;
}