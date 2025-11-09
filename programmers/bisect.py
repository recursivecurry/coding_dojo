import itertools as it
import functools as ft
import collections as cs
import heapq as hq
import math

def bisect_left(xs, x, lo=0, hi=None):
    if hi == None:
        hi = len(xs)
    while lo < hi:
        mid = (hi + lo) // 2
        if x <= xs[mid]:
            hi = mid
        else:
            lo = mid + 1
    return lo

def bisect_right(xs, x, lo=0, hi=None):
    if hi == None:
        hi = len(xs)
    while lo < hi:
        mid = (hi + lo) // 2
        if x < xs[mid]:
            hi = mid
        else:
            lo = mid + 1
    return lo


import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEquals(bisect_left([1, 2, 3, 3, 5], 3), 2)
        self.assertEquals(bisect_left([1, 2, 3, 3, 5], 1), 0)
        self.assertEquals(bisect_left([1, 2, 3, 3, 5], 6), 5)

if __name__ == '__main__':
    unittest.main()