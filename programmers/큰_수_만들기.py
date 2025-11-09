import itertools as it
import functools as ft
import collections as cs
import heapq as hq
import math

def solution(number, k):
    stack = []
    for c in number:
        while k > 0 and stack and stack[-1] < c:
            stack.pop()
            k -= 1
        stack.append(c)
    if k > 0:
        stack = stack[:-k]
    return ''.join(stack)


import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEqual(solution('1924', 2), '94')
        self.assertEqual(solution('1231234', 3), '3234')
        self.assertEqual(solution('4177252841', 4), '775841')

if __name__ == '__main__':
    unittest.main()