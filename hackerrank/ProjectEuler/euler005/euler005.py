# HACKERRANK: Project Euler #5: Smallest multiple
# https://www.hackerrank.com/contests/projecteuler/challenges/euler005

primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37]


def solve(n):
    ans = 1
    for p in primes:
        max_p = 1
        v = p
        while v <= n:
            max_p = v
            v *= p
        ans *= max_p
    return ans


if __name__ == "__main__":
    T = int(input())
    for _ in range(T):
        print(solve(int(input())))
