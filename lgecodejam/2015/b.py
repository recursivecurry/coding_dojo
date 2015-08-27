# LGE CODE JAM 2015 - PROBLEM B


def solve():
    N, K, W = tuple(int(n) for n in input().split())
    PS = tuple(tuple(int(n) for n in input().split()) for l in range(N))

    if N-2 <= K:
        return "YES"

    ans = "NO"
    sorted_ps = sorted(PS, key=lambda p:p[1])
    for k in range(K+1):
        target_ps = sorted_ps[k:N-(K-k)]

    return ans


def main():
    T = int(input())
    for t in range(T):
        print(solve())

