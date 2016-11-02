def solve1(ns):
    current_max = 0
    size = len(ns)
    for i in range(size):
        for j in range(i, size):
            print(ns[i:j+1])
            subsum = sum(ns[i:j+1])
            current_max = subsum if subsum > current_max else current_max
    return current_max


def solve2(ns, lo, hi):
    if lo == hi:
        return ns[lo]
    mid = (lo + hi) // 2
    lmax = 0
    total = 0
    for i in range(mid, lo-1, -1):
        total += ns[i]
        lmax = total if total > lmax else lmax
    rmax = 0
    total = 0
    for i in range(mid+1, hi+1, 1):
        total += ns[i]
        rmax = total if total > rmax else rmax
    return max(lmax + rmax, solve2(ns, lo, mid), solve2(ns, mid+1, hi))


def solve3(ns):
    sub_max = 0
    cur_max = 0
    for n in ns:
        cur_max = n if cur_max < 0 else n + cur_max
        sub_max = cur_max if sub_max < cur_max else sub_max
    return sub_max
