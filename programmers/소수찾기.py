import itertools as it
import functools as ft
import collections as cs
import heapq as hq
import math

def solution(numbers):
    ns = set()
    for l in range(1, len(numbers) + 1):
        for n in it.permutations(numbers, l):
            ns.add(int(''.join(n)))
    ns = ns.difference([0, 1])

    ps = primes(max(ns) + 1)
    answer = len(list(filter(lambda x: x in ps, ns)))
    return answer

def primes(n):
    answer = []
    f = dict()
    for i in range(2, n + 1):
        if i not in f:
            answer.append(i)
        ss = f.pop(i, [i])
        for s in ss:
            f.setdefault(s + i, []).append(s)
    return answer

def is_prime(n):
    answer = []
    f = dict()
    for i in range(2, int(math.sqrt(n)) + 1):
        if i not in f:
            if n % i == 0:
                return False
            answer.append(i)
        ss = f.pop(i, [i])
        for s in ss:
            f.setdefault(s + i, []).append(s)
    return True

if __name__ == '__main__':
    for n in range(2, 20):
        print(n, primes(n))
        print(n, is_prime(n))


#
# import unittest
#
# class Test(unittest.TestCase):
#     def test(self):
#         self.assertEqual(solution([3, 0, 6, 1, 5]), 3)
#         self.assertEqual(solution([4, 0, 6, 1, 5]), 3)
#         self.assertEqual(solution([2, 0, 6, 1, 5]), 2)
#         self.assertEqual(solution([1,1,1]), 1)
#         self.assertEqual(solution([2,2,2]), 2)
#         self.assertEqual(solution([3,3,3]), 3)
#         self.assertEqual(solution([4,4,4]), 3)
#         return
#
# if __name__ == '__main__':
#     unittest.main()