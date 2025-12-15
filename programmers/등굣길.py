import itertools as it
import functools as ft
import collections as cs
import heapq as hq
import math

def solution(w, h, puddles):
    dp = [[-1]*h for _ in range(w)]
    dp[0][0] = 1
    for p in puddles:
        dp[p[0]-1][p[1]-1] = 0
    for l in range(1, w+h-1):
        for x in range(w):
            y = l - x
            if y < 0 or y >= h:
                continue
            if dp[x][y] != 0:
                if x == 0:
                    dp[x][y] = dp[x][y-1]
                elif y == 0:
                    dp[x][y] = dp[x-1][y]
                else:
                    dp[x][y] = (dp[x-1][y] + dp[x][y-1]) % 1_000_000_007

    return dp[w-1][h-1]

import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEquals(solution(4, 3, [[2,2]]), 4)

if __name__ == '__main__':
    unittest.main()