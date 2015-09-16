from operator import itemgetter


def solve(n, m, ps):
    skip = False
    count = 0
    for (p0, p1) in zip(ps, ps[1:]):
        if not skip:
            d =  (p1[0] - p0[0]) + (0 if p1[1] == p0[1] else 1)
            count += d
            skip = True
        else:
            skip = False
    return count


if __name__ == "__main__":
    N, M = tuple(int(n) for n in input().split())
    PS = sorted(tuple( tuple(int(p) for p in  input().split()) for m in range(M)))
    print(solve(N, M, PS))
