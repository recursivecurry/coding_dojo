# HACKERRANK: Project Euler #1: Largest Prime factor
# https://www.hackerrank.com/contests/projecteuler/challenges/euler003
import math

def update_limit(value):
    return int(math.sqrt(value))

def solve(value):
    D = {}
    q = 2
    ans = 0
    limit = update_limit(value)

    while q <= limit:
        if q not in D:
            while value % q == 0:
                value //= q
                ans = q
            limit = update_limit(value)
            D[q * q] = [q]
        else:
            for p in D[q]:
                D.setdefault(p + q, []).append(p)
            del D[q]
        q += 1
    if value != 1:
        ans = value
    return ans

if __name__ == "__main__":
    test = int(input())
    for _ in range(test):
        print(solve(int(input())))
