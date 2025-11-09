import itertools as it
import functools as ft
import collections as cs
import heapq as hq

class DualHeap:
    def __init__(self):
        self.minh = []
        self.maxh = []
        self.deleted = set()
        self.count = 1
    def push(self, v):
        hq.heappush(self.minh, (v, self.count))
        hq.heappush(self.maxh, (-v, self.count))
        self.count += 1
    def _clean(self, h):
        while h and h[0][1] in self.deleted:
            hq.heappop(h)
    def pop_min(self):
        self._clean(self.minh)
        if not self.minh:
            return 0
        val, count = hq.heappop(self.minh)
        self.deleted.add(count)
        return val
    def pop_max(self):
        self._clean(self.maxh)
        if not self.maxh:
            return 0
        val, count = hq.heappop(self.maxh)
        self.deleted.add(count)
        return -val


def solution(operations):
    H = DualHeap()
    for o in operations:
        op = o.split(' ')
        if op[0] == 'I':
            H.push(int(op[1]))
        else:
            if op[1] == '1':
                H.pop_max()
            else:
                H.pop_min()

    return [H.pop_max(), H.pop_min()]

import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEqual(solution(["I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"]), [0, 0])
        self.assertEqual(solution(["I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"]), [333, -45])
        return

if __name__ == '__main__':
    unittest.main()