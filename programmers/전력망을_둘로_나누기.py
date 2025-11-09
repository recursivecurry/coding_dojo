import itertools as it
import functools as ft
import collections as cs
import heapq as hq
import math


def solution(n, wires):
    G = cs.defaultdict(list)
    for n1, n2 in wires:
        G[n1].append(n2)
        G[n2].append(n1)

    P = [0] * (n + 1)
    S = [0] * (n + 1)
    A = n

    def dfs(nd, p):
        nonlocal A
        P[nd] = p
        size = 1
        for c in G[nd]:
            if c == p:
                continue
            size += dfs(c, nd)

        if abs(size - (n - size)) < A:
            A = abs(size - (n - size))
        return size

    dfs(1, 0)
    return A


import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEqual(solution(9, [[1,3],[2,3],[3,4],[4,5],[4,6],[4,7],[7,8],[7,9]]), 3)
        # self.assertEqual(solution(4, [[1,2],[2,3],[3,4]]), 3)
        # self.assertEqual(solution(7, [[1,2],[2,7],[3,7],[3,4],[4,5],[6,7]]), 1)

if __name__ == '__main__':
    unittest.main()