n = int(input())
xs = tuple(int(x) for x in input().split(' '))
first, last = xs[0], xs[n-1]

for i, x in enumerate(xs):
    if i == 0:
        print(xs[1]-x, last-x)
    elif i == n-1:
        print(x - xs[-2], x-first)
    else:
        print(min(x-xs[i-1], xs[i+1]-x), max(x-first, last-x))
