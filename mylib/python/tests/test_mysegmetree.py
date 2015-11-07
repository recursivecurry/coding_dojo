import unittest

import mysegtree as st


class TestBisect(unittest.TestCase):

    max_odd_tree = st.SegmentTree([1,2,3,4,5], max)
    max_even_tree = st.SegmentTree([1,2,3,4,5,6], max)
    sum_odd_tree = st.SegmentTree([3,1,4,2,7], lambda a,b: a+b)
    sum_even_tree = st.SegmentTree([3,1,4,2,7,0], lambda a,b: a+b)

    def test_segtree_build(self):
        self.assertEqual(self.max_odd_tree._tree, [None,5,5,2,4,5,1,2,3,4,5,None])
        self.assertEqual(self.max_even_tree._tree, [None, 6,5,6,3,5,6,1,2,3,4,5,6,None])
        self.assertEqual(self.sum_odd_tree._tree, [None,17,13,4,6,7,3,1,4,2,7,None])
        self.assertEqual(self.sum_even_tree._tree, [None, 17,14,3,5,9,0,3,1,4,2,7,0,None])

    def test_segtree_get(self):
        self.assertEqual(self.max_odd_tree.get(0), 1)
        self.assertEqual(self.max_odd_tree.get(0, 0), 1)
        self.assertEqual(self.max_odd_tree.get(0, 1), 2)
        self.assertEqual(self.max_odd_tree.get(0, 3), 4)
        self.assertEqual(self.max_odd_tree.get(1, 2), 3)
        self.assertEqual(self.max_odd_tree.get(1, 3), 4)
        self.assertEqual(self.max_odd_tree.get(0, 4), 5)
        self.assertEqual(self.sum_even_tree.get(0), 3)
        self.assertEqual(self.sum_even_tree.get(0, 0), 3)
        self.assertEqual(self.sum_even_tree.get(0, 1), 4)
        self.assertEqual(self.sum_even_tree.get(0, 2), 8)
        self.assertEqual(self.sum_even_tree.get(1, 2), 5)
        self.assertEqual(self.sum_even_tree.get(1, 3), 7)
        self.assertEqual(self.sum_even_tree.get(0, 4), 17)

    def test_segtree_update(self):
        max_even_update_tree = st.SegmentTree([1,2,3,4,5,6], max)
        sum_odd_update_tree = st.SegmentTree([3,1,4,2,7], lambda a,b: a+b)
        self.assertEqual(max_even_update_tree.get(0, 1), 2)
        self.assertEqual(max_even_update_tree.get(0, 3), 4)
        self.assertEqual(max_even_update_tree.get(1, 2), 3)
        max_even_update_tree.update(1, 0)
        self.assertEqual(max_even_update_tree.get(0, 1), 1)
        self.assertEqual(max_even_update_tree.get(0, 3), 4)
        self.assertEqual(max_even_update_tree.get(1, 2), 3)
        max_even_update_tree.update(2, 5)
        self.assertEqual(max_even_update_tree.get(0, 1), 1)
        self.assertEqual(max_even_update_tree.get(0, 3), 5)
        self.assertEqual(max_even_update_tree.get(1, 2), 5)

        self.assertEqual(sum_odd_update_tree.get(0, 1), 4)
        self.assertEqual(sum_odd_update_tree.get(0, 3), 10)
        self.assertEqual(sum_odd_update_tree.get(1, 2), 5)
        sum_odd_update_tree.update(1, 0)
        self.assertEqual(sum_odd_update_tree.get(0, 1), 3)
        self.assertEqual(sum_odd_update_tree.get(0, 3), 9)
        self.assertEqual(sum_odd_update_tree.get(1, 2), 4)
        sum_odd_update_tree.update(2, 5)
        self.assertEqual(sum_odd_update_tree.get(0, 1), 3)
        self.assertEqual(sum_odd_update_tree.get(0, 3), 10)
        self.assertEqual(sum_odd_update_tree.get(1, 2), 5)


if __name__ == '__main__':
    unittest.main()
