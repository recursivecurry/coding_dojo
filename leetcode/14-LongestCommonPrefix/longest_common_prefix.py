class Solution(object):
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        import itertools as it
        return "".join([a[0] for a in it.takewhile(lambda cs: all(c==cs[0] for c in cs), zip(*strs))])
