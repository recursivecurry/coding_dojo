import itertools as it
import functools as ft
import collections as cs
import heapq as hq
import math

def gen_all(p):
    vs = [p[0]+p[1], p[0]-p[1], p[0] * p[1]]
    if p[1] != 0:
        vs.append(p[0] // p[1])
    return vs

def solution(N, number):
    dp = [set() for _ in range(9)]
    for i in range(1, 9):
        dp[i].add(int(str(N)*i))
    for n in range(2, 9):
        for l in range(1, n):
            for p in it.product(dp[l], dp[n-l]):
                dp[n].update(gen_all(p))
        if number in dp[n]:
            return n

    return -1

import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEquals(solution(5, 12), 4)
        self.assertEquals(solution(2, 11), 3)

if __name__ == '__main__':
    unittest.main()