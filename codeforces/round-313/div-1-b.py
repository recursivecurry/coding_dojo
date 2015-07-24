# Codeforces Round #313 (Div. 1) B. Equivalent Strings
# http://codeforces.com/contest/559/problem/B
input1 = input()
input2 = input()

def sort(s, length):
    half, one = divmod(length, 2)
    if one == 1:
        return s
    else:
        s1, s2 = sort(s[:half], half), sort(s[half:], half)
        if s1 < s2:
            return s1 + s2
        else:
            return s2 + s1


if sort(input1, len(input1)) == sort(input2, len(input2)):
    print("YES")
else:
    print("NO")
