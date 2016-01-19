def solve(n):
    mx = -1
    for x in range(1, n-1):
        if (n**2-2*n*x) % (2 * n - 2 * x) == 0:
            y = (n**2-2*n*x) // (2 * n - 2 * x)
            xyz = x * y * (n-x-y)
            mx = xyz if 0 < xyz and mx < xyz else mx
    return mx


if __name__ == "__main__":
    C = int(input())
    for _ in range(C):
        print(solve(int(input())))
