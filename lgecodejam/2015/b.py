# -*- encoding=utf-8 -*-
import operator as op
import bisect as bs
#from sortedcontainers import SortedList


def possible(points, left_out, right_out, w):
    bottom_in = points[0][1] + w
    top_in = points[-1][1] - w
    left_in = left_out + w
    right_in = right_out - w

    points_y = tuple(p[1] for p in points)
    bottom_point = bs.bisect_right(points_y, points[0][1]+w)
    top_point = bs.bisect_left(points_y, points[-1][1]-w)
    points_without_top_bottom = points[bottom_point:top_point]

    for p in points_without_top_bottom:
        if left_in < p[0] and p[0] < right_in:
            return False
    return True

def solve(n, k, w, points):
    x_sorted_points = sorted(points, key=op.itemgetter(0, 1))
    y_sorted_points = sorted(points, key=op.itemgetter(1, 0))
    left_out = x_sorted_points[0][0]
    right_out = x_sorted_points[-1][0]

    for bottom_k in range(0, k+1):
        cleaned_points = y_sorted_points[bottom_k:n-k+bottom_k]
        if possible(cleaned_points, left_out, right_out, w):
            return True
        else:
            return False


if __name__ == '__main__':
    T = int(input())
    for _ in range(T):
        N, K, W = tuple(int(n) for n in input().split())
        PS = tuple(tuple(int(c) for c in input().split()) for i in range(N))
        if solve(N, K, W, PS):
            print("YES")
        else:
            print("NO")
