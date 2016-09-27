# LEETCODE: Find Median from Data Stream
# https://leetcode.com/problems/find-median-from-data-stream/

import heapq


class MedianFinder:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.left = []
        self.right = []

    def addNum(self, num):
        """
        Adds a num into the data structure.
        :type num: int
        :rtype: void
        """
        if len(self.left) == 0:
            heapq.heappush(self.left, (-num, num))
        elif self.left[0][1] < num:
            heapq.heappush(self.right, (num, num))
        else:
            heapq.heappush(self.left, (-num, num))

        while True:
            if len(self.left) + 1 < len(self.right):
                temp = heapq.heappop(self.right)
                heapq.heappush(self.left, (-temp[1], temp[1]))
            elif len(self.left) > len(self.right) + 1:
                temp = heapq.heappop(self.left)
                heapq.heappush(self.right, (temp[1], temp[1]))
            else:
                break

    def findMedian(self):
        """
        Returns the median of current data stream
        :rtype: float

        Sample:
        >>> mf = MedianFinder()

        >>> mf.addNum(1)

        >>> mf.addNum(2)

        >>> mf.findMedian()
        1.5
        >>> mf.addNum(3)

        >>> mf.findMedian()
        2
        """
        if len(self.left) == len(self.right):
            return (self.left[0][1] + self.right[0][1]) / 2.0
        elif len(self.left) < len(self.right):
            return self.right[0][1]
        else:
            return self.left[0][1]


if __name__ == "__main__":
    import doctest
    doctest.testmod()
