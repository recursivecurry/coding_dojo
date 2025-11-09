import itertools as it
import functools as ft
import collections as cs
import heapq as hq
import math

def solution(people, limit):
    people.sort()
    l = 0
    h = len(people) - 1
    answer = 0
    while l <= h:
        if people[l] + people[h] <= limit:
            l += 1
            h -= 1
        else:
            h -= 1
        answer +=  1

    return answer


import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEqual(solution([70, 50, 80, 50], 100), 3)
        self.assertEqual(solution([70, 50, 80], 100), 3)
        self.assertEqual(solution([50, 50], 100), 1)
        self.assertEqual(solution([10], 100), 1)

if __name__ == '__main__':
    unittest.main()