# HACKERRANK: The Grid Search
# https://www.hackerrank.com/challenges/the-grid-search

import functools as ft

def solve(large, small):
    larges = tuple(large[i:] for i in range(len(small)))
    for large_part in zip(*larges):
        idxs = set(l[0].find(l[1]) for l in zip(large_part, small))
        if len(idxs) == 1 and -1 not in idxs :
            return True
    return False


if __name__ == "__main__":
    T = int(input())
    for _ in range(T):
        LR, LC = tuple(int(n) for n in input().split())
        L = tuple(input() for _ in range(LR))
        SR, SC = tuple(int(n) for n in input().split())
        S = tuple(input() for _ in range(SR))
        print("YES" if solve(L, S) else "NO")
