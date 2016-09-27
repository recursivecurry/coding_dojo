class Solution(object):
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        if x < 0:
            return -self.reverse(-x)
        else:
            r = 0
            while x > 0:
                r *= 10
                x, m = divmod(x, 10)
                r += m
            if r > 2 ** 31:
                return 0
            else:
                return r

