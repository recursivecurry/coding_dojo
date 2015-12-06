# HACKERRANK: Project Euler #8: Largest product in a series
# https://www.hackerrank.com/contests/projecteuler/challenges/euler008
import functools as F


def find_max(k, ns):
    zipped_list = tuple(ns[i:] for i in range(k))
    product_list = tuple(F.reduce(lambda a,b:a*b, ks)for ks in zip(*zipped_list))
    return max(product_list)


if __name__ == "__main__":
    T = int(input())
    for _ in range(T):
        N, K = tuple(int(v) for v in input().split())
        NS = tuple(int(v) for v in input())
        print(find_max(K, NS))
