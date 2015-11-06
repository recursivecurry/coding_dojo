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
        if x < a[mid]:
            hi = mid
        else:
            lo = mid + 1
    return lo


if __name__ == "__main__":
    sample = [0, 5, 5, 10, 10, 10, 15, 20]
    print(sample)
    print("TARGET: 7")
    print(bisect.bisect_left(sample, 7))
    print(bisect_left(sample, 7))
    print(bisect.bisect_right(sample, 7))
    print(bisect_right(sample, 7))
    print("TARGET: 10")
    print(bisect.bisect_left(sample, 10))
    print(bisect_left(sample, 10))
    print(bisect.bisect_right(sample, 10))
    print(bisect_right(sample, 10))
