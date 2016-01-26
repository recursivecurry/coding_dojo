import itertools as it
import bisect


def gen_primes():
    D = {}
    n = 2
    while True:
        if n not in D:
            yield n
            D[n * n] = [n]
        else:
            for p in D[n]:
                D.setdefault(p + n, []).append(p)
            del D[n]
        n += 1


if __name__ == "__main__":
    ps = tuple(it.takewhile(lambda x: x<=1000000, gen_primes()))
    ss = tuple(it.accumulate(ps))

    N = int(input())
    for _ in range(N):
        M = int(input())
        print(ss[bisect.bisect_right(ps, M)-1])
