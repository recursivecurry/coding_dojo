import itertools as it
import functools as ft
import collections as cs
import heapq as hq
import math

def solution(distance, rocks, n):
    rocks.sort()
    rocks.append(distance)
    lo, hi = 0, distance
    while lo < hi:
        mid = (lo+hi) // 2
        if n < removed(n, rocks, mid) <= n:
            answer = mid
            lo = mid + 1
        else:
            hi = mid - 1
    return answer

def removed(n, rocks, width):
    count = 0
    prev = 0
    for r in rocks:
        if r - prev < width:
            count += 1
            if count > n:
                break;
        else:
            prev = r

    return count

import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEquals(solution(25, [2, 14, 11, 21, 17], 2), 4)

if __name__ == '__main__':
    unittest.main()