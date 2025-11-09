import itertools as it
import functools as ft
import collections as cs

def solution(prices):
    answer = [0] * len(prices)
    S = []
    for c in enumerate(prices):
        while S and S[-1][1] > c[1]:
            p = S.pop()
            answer[p[0]] = c[0] - p[0]
        S.append(c)

    for s in S:
        answer[s[0]] = len(prices) - 1 - s[0]

    return answer

import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEqual(solution([1, 2, 3, 2, 3]), [4, 3, 1, 1, 0])
        return

if __name__ == '__main__':
    unittest.main()