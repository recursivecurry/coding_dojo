import itertools as it
import functools as ft
import collections as cs
import heapq as hq
import math

def solution(name):
    diff = lambda c: min(ord('z') - ord(c)+1, ord(c) - ord('a'))
    count = 0
    for c in name:
        count += diff(c)

    distance = len(name) - 1
    begin = 0
    for i in range(len(name)):
        j = i + 1
        while j < len(name) and name[j] == 'A':
            j += 1
        distance = min(distance, i + i + len(name) - j, len(name) - j + len(name) - j + i)
    return distance + count


import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEqual(solution('JEROEN'), 56)
        self.assertEqual(solution('JAN'), 23)

if __name__ == '__main__':
    unittest.main()