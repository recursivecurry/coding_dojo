import heapq

def disk_controller(jobs):
    jobs.sort()
    now = 0
    total = 0
    h = []
    for (i, j) in enumerate(jobs):
        if j[0] <= now:
            heapq.heappush(h, (j[1], j[0], i))
        else:
            while h and j[0] > now:
                c = heapq.heappop(h)
                if now < c[1]:
                    now = c[1]
                total += (now + c[0] - c[1])
                now += c[0]
            heapq.heappush(h, (j[1], j[0], i))
    while h:
        c = heapq.heappop(h)
        if now < c[1]:
            now = c[1]
        total += (now + c[0] - c[1])
        now += c[0]
    return total // len(jobs)


import unittest

class TestSolution(unittest.TestCase):
    def test_case_disk_controller_1(self):
        self.assertEqual(disk_controller([[0, 3], [1, 9], [3, 5]]), 8)
    def test_case_disk_controller_2(self):
        self.assertEqual(disk_controller([[0, 3], [1, 9], [2, 6]]), 9)

if __name__ == '__main__':
    unittest.main()