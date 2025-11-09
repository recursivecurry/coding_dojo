from collections import deque

def solution(n, edge):
    from collections import deque
    distance = [n for node in range(n)]
    graph = {}
    for n1, n2 in edge:
        graph.setdefault(n1-1, []).append(n2-1)
        graph.setdefault(n2-1, []).append(n1-1)
    queue = deque([0])
    distance[0] = 0
    farthest = 0
    while queue:
        current = queue.popleft()
        for candidate in graph[current]:
            new_distance = distance[current] + 1
            if distance[candidate] > new_distance:
                distance[candidate] = new_distance
                if new_distance > farthest:
                    farthest = new_distance
                queue.append(candidate)
    return distance.count(farthest)


import unittest

class Test(unittest.TestCase):
    def test_1(self):
        self.assertEqual(solution(6, [[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]), 3)

if __name__ == '__main__':
    unittest.main()