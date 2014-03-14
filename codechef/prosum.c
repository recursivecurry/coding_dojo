#include <stdio.h>
 
int main (int argc, char *argv[])
{
	long long T;
	long long N;
	long long i, j;
 
	scanf("%lld", &T);
	for (i=0; i<T; i++)
	{
		long long last;
		long long result = 0;
		long long two = 0;
		long long gt_two = 0;
 
		scanf("%lld", &N);
		for (j=0; j<N; j++)
		{
			scanf("%lld", &last);
			switch (last) {
				case 2:
					result += gt_two;
					two++;
					break;
				default:
					result += (two+gt_two);
					gt_two++;
					break;
				case 0:
				case 1:
					break;
			}
		}
		printf("%lld\n", result);
	}
} 