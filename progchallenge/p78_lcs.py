
def solve(n, m, s, t, c={}):
    if (n, m) in c:
        return c[(n, m)]
    elif 0 in (n, m):
        return 0
    else:
        if s[n-1] == t[m-1]:
            return max(solve(n-1, m-1, s, t)+1, solve(n-1, m, s, t), solve(n, m-1, s, t))
        else:
            return max(solve(n-1, m, s, t), solve(n, m-1, s, t))


if __name__ == "__main__":
    N = int(input())
    M = int(input())
    S = input()
    T = input()
    print(solve(N, M, S, T, {}))
