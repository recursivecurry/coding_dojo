# HACKERRANK: Maximise Sum
# https://www.hackerrank.com/challenges/maximise-sum
import bisect


def scanl(f, base, l):
    for x in l:
        base = f(base, x)
        yield base


def solve(m, es):
    ms = tuple(scanl(lambda a, b: (a+b) % m, 0, es))
    pre_sum = []
    max_value = 0

    for n in ms:
        max_value = max(max_value, n)
        pos = bisect.bisect_right(pre_sum, n)
        if pos < len(pre_sum):
            max_value = max(max_value, n - pre_sum[pos] + m)
        bisect.insort_right(pre_sum, n)

    return max_value


def main():
    T = int(input())
    for _ in range(T):
        N, M = tuple(int(v) for v in input().split())
        ES = tuple(int(v) for v in input().split())
        print(solve(M, ES))


if __name__ == "__main__":
    main()
