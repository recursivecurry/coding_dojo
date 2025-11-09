import itertools as it
import functools as ft
import collections as cs
import heapq as hq
import math


def solution(n, times):
    times.sort()
    lo, hi = 1, times[-1]*n
    while lo < hi:
        mid = (lo + hi) // 2
        p = 0
        for t in times:
            p += (mid // t)
        if n <= p:
            hi = mid
        else:
            lo = mid + 1
    return lo


import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEquals(solution(6, [7, 10]), 28)
        self.assertEquals(solution(8, [2, 3, 6, 7]), 8)

if __name__ == '__main__':
    unittest.main()