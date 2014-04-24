#include <cstdio>
#include <set>

using namespace std;

int main(int argc, char *argv[]) {
	int T;

	scanf("%d", &T);
	while (T--) {
		int N, K, P;
		bool answer = true;
		set<int> KS;
		scanf("%d %d %d", &N, &K, &P);
		while (K--) {
			int l, r;
			scanf("%d %d", &l, &r);
			KS.insert(l);
		}
		while (P--) {
			int a, b;
			scanf("%d %d", &a, &b);
			if ( answer && KS.find(a)==KS.end())
				answer = false;
		}
		if (answer)
			printf("Yes\n");
		else
			printf("No\n");
	}
	return 0;
}