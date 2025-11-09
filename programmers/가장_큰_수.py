import itertools as it
import functools as ft
import collections as cs
import heapq as hq

def lt(a, b):
    s1 = a + b
    s2 = b + a
    return (int(s1) > int(s2)) - (int(s1) < int(s2))

def solution(numbers):
    answer = ''.join(sorted([str(n) for n in numbers], key=ft.cmp_to_key(lt), reverse=True))
    return str(int(answer))

import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEqual(solution([6, 10, 2]), "6210")
        self.assertEqual(solution([3, 30, 34, 5, 9]), "9534330")
        return

if __name__ == '__main__':
    unittest.main()