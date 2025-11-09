import itertools as it
import functools as ft
import collections as cs
import heapq as hq
import math

def solution(k, dungeons):
    dungeons = {(d[0],d[1]) for d in dungeons}
    answer = dfs(k, 0, dungeons)
    return answer

def dfs(k, count, dungeons):
    print(k, count, dungeons)
    max_value = count
    for d in dungeons:
        if k >= d[0]:
            if k-d[1] < 0:
                continue
            dungeons.remove(d)
            v = dfs(k-d[1], count+1, dungeons)
            dungeons.add(d)
            if v > max_value:
                max_value = v
    return max_value


import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEqual(solution(80, [[80,20],[50,40],[30,10]]), 3)
        return

if __name__ == '__main__':
    unittest.main()