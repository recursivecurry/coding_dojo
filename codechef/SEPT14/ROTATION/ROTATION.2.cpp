#include <cstdio>
#include <vector>
#include <utility>

using namespace std;

int A[100000];

int main (int argc, char *argv[]) {
	int N, M;
	int pos=0;

	scanf("%d %d", &N, &M);

	for (int idx=0; idx<N; idx++) {
		scanf("%d", A+idx);
	}

	while (M--) {
		char c;
		int val;
		scanf("%c", &c);
		scanf("%d", &val);
		scanf("%c", &c);
		scanf("%d", &val);
		switch (c) {
			case 'A':
				val = N - val;
			case 'C':
				pos = (pos +  val) % N;
				break;
			case 'R':
				printf("%d\n", A[(pos+val-1)%N]);
				break;
		}
	}

	return 0;
}