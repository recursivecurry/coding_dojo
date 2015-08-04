four, five, six = tuple(int(n) for n in input().split(' '))

MOD = int(1e9 + 7)

sum_map = [None] * (101 * 101 * 101)
num_map = [None] * (101 * 101 * 101)


def sum_func(x, y, z):
    if x < 0 or y < 0 or z < 0:
        return 0
    else:
        if sum_map[x * (101*101) + y * 101 + z] is None:
            sum_map[x * (101*101) + y * 101 + z] = \
                sum_func(x-1, y, z) * 10 % MOD + num_func(x-1, y, z) * 4 % MOD \
                + sum_func(x, y-1, z) * 10 % MOD + num_func(x, y-1, z) * 5 % MOD \
                + sum_func(x, y, z-1) * 10 % MOD + num_func(x, y, z-1) * 6 % MOD
        return sum_map[x * (101*101) + y * 101 + z]


def num_func(x, y, z):
    if x < 0 or y < 0 or z < 0:
        return 0
    elif x == 0 and y == 0 and z == 0:
        return 1
    else:
        if num_map[x * (101*101) + y * 101 + z] is None:
            num_map[x * (101*101) + y * 101 + z] = (num_func(x-1, y, z)
                                                    + num_func(x, y-1, z)
                                                    + num_func(x, y, z-1)) \
                % MOD
        return num_map[x * (101*101) + y * 101 + z]

ans = 0
for x in range(four+1):
    for y in range(five+1):
        for z in range(six+1):
            ans += sum_func(x, y, z)
            ans %= MOD

print(ans)
