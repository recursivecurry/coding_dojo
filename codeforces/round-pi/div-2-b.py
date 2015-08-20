from collections import defaultdict
from array import array
from itertools import repeat

def bit_add(bit, idx, val):
    """
    >>> bit_add([0, 0, 0, 0, 0, 0, 0, 0], 2, 1)
    [0, 0, 1, 1, 0, 0, 0, 1]
    """
    idx += 1
    while idx <= len(bit):
        bit[idx-1] += val
        idx += (idx & -idx)
    return bit


def bit_sum(bit, idx):
    """
    >>> bit_sum([0, 1, 1, 2, 0, 0, 0, 2], 3)
    2
    """
    s = 0
    idx += 1
    while 0 < idx:
        s += bit[idx-1]
        idx -= (idx & -idx)
    return s


def get_start(n, k):
    """
    >>> get_start(10, 3)
    (10, 1)
    >>> get_start(63, 3)
    (7, 3)
    >>> get_start(-6, 3)
    (-2, 2)
    """
    if k == 1:
        return n, 1
    c= 1
    while n % k == 0:
        n //= k
        c+= 1
    return n, c


def main():
    N, K = tuple(int(n) for n in input().split())
    NS = tuple(int(n) for n in input().split())
    seqs = defaultdict(lambda: [])
    for n in NS:
        s, c = get_start(n, K)
        if s < 0:
            seqs[s].append(31 - c)
        else:
            seqs[s].append(c)
    ans = 0
    for seq in seqs.values():
        if K == 1:
            l = len(seq)
            if 3 <= l:
                ans += (l*(l-1)*(l-2))//6
        else:
            km = [[0]*32, [0]*32, [0]*32]
            for s in seq:
                for k in (0, 1, 2):
                    val = 1 if k == 0 else bit_sum(km[k-1],s-1)
                    km[k] = bit_add(km[k], s, val)
            ans += km[2][31]

    print(ans)


if __name__ == '__main__':
    #import doctest
    #doctest.testmod()
    main()
