def solution(n, computers):
    import collections as cs
    node = [1 for i in range(n)]
    node[0] = 0
    q = cs.deque([0])
    count = 0
    while q:
        current = q.popleft()
        print(current, q, computers[current])
        for i, j in enumerate(computers[current]):
            if current != i and j == 1 and node[i] == 1:
                q.append(i)
                node[i] = 0
        if not q:
            count += 1
            for i, j in enumerate(node):
                if j == 1:
                    node[i] = 0
                    q.append(i)
    return count

import unittest

class TestSolution(unittest.TestCase):
    def test_1(self):
        self.assertEqual(solution(3, [[1,1,0],[1,1,0],[0,0,1]]), 2)
        self.assertEqual(solution(3, [[1, 1, 0], [1, 1, 1], [0, 1, 1]]), 1)

if __name__ == '__main__':
    unittest.main()