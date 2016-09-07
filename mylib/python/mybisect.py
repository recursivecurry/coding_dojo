import bisect


def bisect_left(a, x, lo=0, hi=None):
    """
    """
    if hi is None:
        hi = len(a)

    while (lo < hi):
        mid = (lo + hi) // 2
        if a[mid] < x:
            lo = mid + 1
        else:
            hi = mid
    return lo


def bisect_right(a, x, lo=0, hi=None):
    """
    """
    if hi is None:
        hi = len(a)

    while (lo < hi):
        mid = (lo + hi) // 2
        if a[mid] <= x:
            lo = mid + 1
        else:
            hi = mid
    return lo
