n = int(input())
cur = cap =  0
s = set()
for i in range(n):
    m, n = input().split(' ')
    if m == '+':
        cur += 1
        s.add(n)
        if cap < cur:
            cap = cur
    else:
        if n in s:
            cur -= 1
            s.remove(n)
        else:
            cap += 1
print(cap)
