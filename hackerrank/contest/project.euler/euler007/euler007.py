import itertools


def gen_primes(num):
    D = {}
    q = 2

    while True:
        if q not in D:
            yield q
            num -= 1
            if 0 == num:
                break
            D[q * q] = [q]
        else:
            for p in D[q]:
                D.setdefault(p + q, []).append(p)
            del D[q]
        q += 1


if __name__ == "__main__":
    primes = tuple(v for i, v in enumerate(itertools.chain((1,), gen_primes(10000))))
    tuple(print(primes[int(input())]) for _ in range(int(input())))
