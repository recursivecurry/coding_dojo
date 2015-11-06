import unittest

import bisect

import mybisect
#from mybisect import bisect_left, bisect_right

class TestBisect(unittest.TestCase):

    sample = [0, 5, 10, 10, 15, 15, 15, 20, 20, 20, 20]

    def test_bisect_left(self):
        self.assertEqual(mybisect.bisect_left(self.sample, 0),
                         bisect.bisect_left(self.sample, 0))
        self.assertEqual(mybisect.bisect_left(self.sample, 5),
                         bisect.bisect_left(self.sample, 5))
        self.assertEqual(mybisect.bisect_left(self.sample, 7),
                         bisect.bisect_left(self.sample, 7))
        self.assertEqual(mybisect.bisect_left(self.sample, 10),
                         bisect.bisect_left(self.sample, 10))

    def test_bisect_right(self):
        self.assertEqual(mybisect.bisect_right(self.sample, 0),
                         bisect.bisect_right(self.sample, 0))
        self.assertEqual(mybisect.bisect_right(self.sample, 5),
                         bisect.bisect_right(self.sample, 5))
        self.assertEqual(mybisect.bisect_right(self.sample, 7),
                         bisect.bisect_right(self.sample, 7))
        self.assertEqual(mybisect.bisect_right(self.sample, 10),
                         bisect.bisect_right(self.sample, 10))


if __name__ == '__main__':
    unittest.main()
