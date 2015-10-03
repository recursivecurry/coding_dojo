# HACKERRANK - Project Euler #4: Largest palindrome product
# https://www.hackerrank.com/contests/projecteuler/challenges/euler004

import bisect
import itertools


all_3digit_product = set(f*s for f in range(100, 1000) for s in range(100, 1000))
palindrome_numbers = filter(lambda n: str(n) == str(n)[::-1] and n < 1000000,
                            all_3digit_product)
sorted_palindrome_numbers = sorted(palindrome_numbers)


if __name__ == "__main__":
    T = int(input())
    for _ in range(T):
        num = int(input())
        print(sorted_palindrome_numbers[bisect.bisect_left(sorted_palindrome_numbers, num)-1])

