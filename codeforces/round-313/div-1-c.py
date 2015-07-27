# Codeforces Round #313 (Div. 1) C. Gerald and Giant Chess
# http://codeforces.com/contest/559/problem/C

MAX = 10 ** 9 + 7

FACT = None


def init_fact(m):
    global FACT
    FACT = (m+1) * [None]
    FACT[0] = 1
    for n in range(1, m+1):
        FACT[n] = FACT[n-1] * n


def combi(t, s):
    return FACT[t] // FACT[t-s] // FACT[s]


def solve(bs):
    bs.sort(key=lambda p: p[0]+p[1])
    # print(bs)
    for i, b in enumerate(bs[1:], 1):
        cur = bs[i]
        for j in range(1, i):
            prev = bs[j]
            if cur[0]+cur[1] <= prev[0]+prev[1]:
                continue
            # temp = cur[2]
            cur[2] -= (prev[2] *
                       combi(cur[0]-prev[0]+cur[1]-prev[1], cur[0]-prev[0]))
            cur[2] += MAX
            cur[2] %= MAX
            # print("cur: ", i, " prev: ", j, " ", temp, " -> ", cur[2])
    # print(bs)
    return bs[-1][2]


if __name__ == "__main__":
    H, W, N = tuple(int(i) for i in input().split(' '))

    init_fact(H+W)

    blacks = [[1, 1, 1], [H, W, combi(H+W-2, H-1)]]

    for i in range(N):
        black = [int(n) for n in input().split(' ')]
        black.append(combi(black[0]+black[1]-2, black[0]-1))
        blacks.append(black)
    print(solve(blacks))
