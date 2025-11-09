import itertools as it
import functools as ft
import collections as cs
import heapq as hq
import math

def solution(routes):
    cam = -40000
    count = 0
    routes.sort(key=lambda x: x[1])
    for begin, end in routes:
        if begin > cam:
            cam = end
            count += 1
    return count


import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEqual(solution([[-20,-15], [-14,-5], [-18,-13], [-5,-3]]), 2)

if __name__ == '__main__':
    unittest.main()