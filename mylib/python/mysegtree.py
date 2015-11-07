class SegmentTree:
    """
    """
    def __init__(self, li, fn):
        self._tree = [None] * (len(li)+1) + li + [None]
        self.__fn = lambda l, r: l if r is None else r if l is None else fn(l, r)
        self.__base = len(li)+1
        self.__build()

    def __build(self):
        for c in range(self.__base-1, 0, -1):
            self._tree[c] = self.__fn(self._tree[c*2], self._tree[c*2+1])

    def update(self, idx, val):
        c = self.__base + idx
        self._tree[c] = val
        c = c // 2
        while 0 < c:
            self._tree[c] = self.__fn(self._tree[c*2], self._tree[c*2+1])
            c = c // 2

    def get(self, lo, hi=None):
        hi = self.__base + (lo if hi is None else hi)
        lo = self.__base + lo
        lval = None
        hval = None
        while lo <= hi:
            if lo % 2 == 1:
                lval = self._tree[lo] if lval is None else self.__fn(lval, self._tree[lo])
                lo = (lo + 1) // 2
            else:
                lo = lo // 2

            if hi % 2 == 0:
                hval = self._tree[hi] if hval is None else self.__fn(self._tree[hi], hval)
                hi = (hi - 1) // 2
            else:
                hi = hi // 2
        return self.__fn(lval, hval)
