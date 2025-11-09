import math
def primes(n):
    answer = set()
    f = dict()
    for i in range(2, int(math.sqrt(n)) + 1):
        if i in f:
            c = f[i]
            c.pop(i, None)
            f[i+c] = c
        else:
            if n % i == 0:
                answer.add(i)
            f[i+i] = i
    return answer

if __name__ == '__main__':
    for n in range(2, 10):
        print(primes(n))

