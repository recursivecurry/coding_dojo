t = int(raw_input())
for c in xrange(t):
    n = int(raw_input())
    ns = [int(x) for x in raw_input().split(' ')]
    ms = [1] * n
    ans = 0
    for ix, x in enumerate(ns):
        for iy, y in enumerate(ns[:ix]):
            if y < x and ms[ix] < ms[iy] + 1:
                ms[ix] = ms[iy] + 1
    print(max(ms))
