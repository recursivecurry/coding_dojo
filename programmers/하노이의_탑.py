import itertools as it
import functools as ft
import collections as cs
from typing import List

def solution(n: int) -> List[List[int]]:
    return hanoi(n, 1, 2, 3)

def hanoi(n: int, s: int, m: int, d: int) -> List[List[int]]:
    if n == 1:
        return [[s, d]]
    else:
        return hanoi(n-1, s, d, m) + [[s,d]] + hanoi(n-1, m, s, d)

import unittest

class SolutionTest(unittest.TestCase):
    def test_1(self):
        self.assertEqual(solution(1), [[1, 3]])
        self.assertEqual(solution(2), [[1, 2], [1, 3], [2, 3]])
        self.assertEqual(solution(3), [[1, 3], [1, 2], [3, 2], [1, 3], [2, 1], [2, 3], [1, 3]])

if __name__ == "__main__":
    unittest.main()
