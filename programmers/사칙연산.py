import itertools as it
import functools as ft
import collections as cs
import heapq as hq
import math


def solution(arr):
    ns = []
    os = []
    for i, s in enumerate(arr):
        if i % 2 == 0:
            ns.append(int(s))
        else:
            os.append(s)
    n = len(ns)
    dp = [[[1000000, -1000000] for _ in range(n)] for _ in range(n)]
    for i in range(n):
        dp[i][i]= [ns[i], ns[i]]
    for l in range(2, n+1):
        for i in range(n-l+1):
            j = i + l - 1
            for k in range(i, j):
                left_min, left_max = dp[i][k]
                right_min, right_max = dp[k+1][j]

                if os[k] == '+':
                    min_val = left_min + right_min
                    max_val = left_max + right_max
                else:
                    min_val = left_min - right_max
                    max_val = left_max - right_min
                dp[i][j][0] = min(dp[i][j][0], min_val)
                dp[i][j][1] = max(dp[i][j][1], max_val)
    return dp[i][j][1]

import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEquals(solution(["1", "-", "3", "+", "5", "-", "8"]), 1)
        self.assertEquals(solution(["5", "-", "3", "+", "1", "+", "2", "-", "4"]), 3)

if __name__ == '__main__':
    unittest.main()