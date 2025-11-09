def solution(priorities, location):
    ps = [p for p in enumerate(priorities)]
    target_priority = priorities[location]
    Q = []
    RQ = []
    order = 0
    for c in range(9, target_priority - 1, -1):
        for p in ps:
            if p[1] == c:
                order += 1
                if p[0] == location:
                    return order
                RQ, Q = RQ + Q, []
            else:
                Q.append(p)
        ps, RQ, Q = Q + RQ, [], []
        # print(ps, order)


import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEqual(solution([2, 1, 3, 2], 2), 1)
        self.assertEqual(solution([1, 1, 9, 1, 1, 1]	, 0), 5)

if __name__ == '__main__':
    unittest.main()