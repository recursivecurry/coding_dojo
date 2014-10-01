#include <iostream>
#include <array>

using namespace std;

int N;
array<int, 20001> C{0, };
array<int, 20001> O{0, };
array<int, 20001> P{0, };

void insertion_sort(array<int, 20001> c) {
	int i, j ,tmp;

	P[C[1]] = 1;

	for (i = 2; i <= N; i++) {
		int now = c[i];
		j = i;
		while (j > 0 && C[j - 1] > c[j]) {
			tmp = c[j];
			c[j] = c[j - 1];
			c[j - 1] = tmp;
			j--;
		}
		if (j < now) {
			P[now] = j;
		} else {
			P[now] = now;
		}
	}
}

void reverse_array(int start, int end)
{
	int temp;
	while(start < end)
	{
		if (start < P[C[end]]) {  // 뒤에서 앞으로 온 차의 순위가 능력보다 높다.
			P[C[end]] = start;
			cout << "1: " << C[end] << " " << P[C[end]] << endl;
		}
		if (end < P[C[start]]) {  // 뒤로 간 위치의 능력보다 위치가 더 높다.
			P[C[start]] = end;
			cout << "2: " << C[start] << " " << P[C[start]] << endl;
		} else {  //순위가 능력보다 위치가 더 낮다.
			int forward = 0;
			int pos = 1;
			while (start+pos <= end && C[start] <= C[start+pos]) {
				forward++;
			}
			P[C[start]] = end - forward;
		}
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
		insertion_sort(C);
		int ans = 0;
		for (int idx=1; idx<=N; idx++) {
			ans += (idx * P[idx]);
			ans %= 10007;
		}
		cout << ans << endl;
		C = O;
		cin >> K;
		cout << "K: " << K << endl;
		while (K--) {
			int s, e;

			cin >> s >> e;

			reverse_array(s, e);
			for (auto c: C)
				cout << c << " ";
			cout << endl;
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