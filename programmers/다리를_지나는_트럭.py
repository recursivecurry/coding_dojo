def solution(bridge_length, weight, truck_weights):
    import collections as cs
    trucks = cs.deque(truck_weights)
    bridge = cs.deque([0]*bridge_length)
    time = 0
    tw = 0
    while trucks:
        time += 1
        ct = bridge.popleft()
        tw -= ct
        if tw + trucks[0] <= weight:
            nt = trucks.popleft()
            bridge.append(nt)
            tw += nt
        else:
            bridge.append(0)
    while tw > 0:
        time += 1
        tw -= bridge.popleft()
    return time

import unittest

class Test(unittest.TestCase):
    def test(self):
        self.assertEqual(solution(2, 10, [7,4,5,6]), 8)
        self.assertEqual(solution(100, 100, [10]), 101)
        self.assertEqual(solution(100, 100, [10,10,10,10,10,10,10,10,10,10]), 110)

if __name__ == '__main__':
    unittest.main()