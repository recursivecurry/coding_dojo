from collections import defaultdict
import functools


def main():
    N, K = tuple(int(n) for n in input().split())
    NS = tuple(int(n) for n in input().split())
    nst = defaultdict(lambda: [0, 0, 0])

    for n in NS:
        if n == 0:
            nst[0][2] += nst[0][1]
            nst[0][1] += nst[0][0]
            nst[0][0] += 1
        else:
            d, m = divmod(n, K)
            if d != 0 and m == 0:
                nst[n][2] += nst[n//K][1]
                nst[n][1] += nst[n//K][0]
            nst[n][0] += 1
    print(functools.reduce(lambda x, y: x + y[2], nst.values(), 0))


if __name__ == '__main__':
    main()
