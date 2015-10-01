# HACKERRANK - Project Euler #4: Largest palindrome product
# https://www.hackerrank.com/contests/projecteuler/challenges/euler004

import itertools

def gen_primes():
    D = {}
    q = 2

    while True:
        if q not in D:
            yield q
            D[q * q] = [q]
        else:
            for p in D[q]:
                D.setdefault(p + q, []).append(p)
            del D[q]
        q += 1

primes = list(itertools.takewhile(lambda v: v<1000, iter(gen_primes())))

palindrome_numbers = sorted((x*100000 + y*10000 + z*1000 + z*100 + y*10 + z for x in range(1, 10) for y in range(10) for z in range(1,10)), reverse=True)

def check_divider(v):
    for p in primes:
        m = 0
        while m == 0:
            d, m = divmod(v, p)
            v = d if m == 0 else v
    return True if v < 1000 else False

answers = list(filter(check_divider, palindrome_numbers))

if __name__ == "__main__":
    T = int(input())
    for _ in range(T):
        num = int(input())
        print(next(itertools.dropwhile(lambda v: num<=v, answers)))

