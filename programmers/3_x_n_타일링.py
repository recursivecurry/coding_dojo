import heapq

def block_puzzle(n):
    if n == 0:
        return 1
    elif n == 2:
        return 3
    elif n % 2 == 1:
        return 0
    i = 4
    fn_2 = 3
    fn_4 = 1
    while i < n:
        fn = (4 * fn_2 - fn_4) % 1_000_000_007
        fn_4 = fn_2
        fn_2 = fn
        i += 2
    return (4 * fn_2 - fn_4) % 1_000_000_007

def block_puzzle2(n):
    if n == 0:
        return 1
    elif n == 2:
        return 3
    elif n % 2 == 1:
        return 0
    i = 4
    fn_2 = 3
    fn_4 = 1
    while i < n:
        fn = (4 * fn_2 - fn_4) % 1_000_000_007
        fn_4 = fn_2
        fn_2 = fn
        i += 2
    return (4 * fn_2 - fn_4) % 1_000_000_007

import unittest

class TestSolution(unittest.TestCase):
    def test_case_1(self):
        self.assertEqual(block_puzzle(2), 3)

    def test_case_2(self):
        self.assertEqual(block_puzzle(4), 11)

    def test_case_3(self):
        self.assertEqual(block_puzzle(6), 41)

    def test_case_4(self):
        self.assertEqual(block_puzzle(0), 1)

    def test_case_5(self):
        self.assertEqual(block_puzzle(1), 0)

if __name__ == '__main__':
    unittest.main()