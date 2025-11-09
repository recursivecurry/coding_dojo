import itertools as it
import functools as ft
import collections as cs
import heapq as hq
import math

def solution_kruskal(n, costs):
    parent = list(range(n))
    size = [1] * n

    def find(m):
        while parent[m] != m:
            parent[m] = parent[parent[m]]
            m = parent[m]
        return m

    def union(a, b):
        ra, rb = find(a), find(b)
        if ra == rb:
            return False
        else:
            if size[ra] < size[rb]:
                ra, rb = rb, ra
            parent[rb] = ra
            size[ra] = size[ra] + size[rb]
            return True
    costs.sort(key=lambda x: x[2])
    total = 0
    count = 0
    for f, t, c in costs:
        if union(f, t):
            total += c
            count += 1
            if count == n - 1:
                break
    return total


def solution_prim(n, costs):
    graph = cs.defaultdict(list)
    for f, t, c in costs:
        graph[f].append((c, t))
        graph[t].append((c, f))
    visited = [False] * n
    visited[0] = True
    count = 1
    total = 0
    heap = []
    for c, t in graph[0]:
        hq.heappush(heap, (c, 0, t))
    while count < n and heap:
        c, f, t = hq.heappop(heap)
        if visited[t]:
            continue
        visited[t] = True
        count += 1
        total += c
        for nc, nt in graph[t]:
            if not visited[nt]:
                hq.heappush(heap, (nc, t, nt))
    return total

import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEqual(solution_kruskal(4, [[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]]), 4)
        self.assertEqual(solution_kruskal(3, [[0,2,2],[0,1,1],[1,2,1]]), 2)
        self.assertEqual(solution_prim(4, [[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]]), 4)
        self.assertEqual(solution_prim(3, [[0,2,2],[0,1,1],[1,2,1]]), 2)

if __name__ == '__main__':
    unittest.main()