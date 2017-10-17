#!/bin/python3

import sys

s = [0]
while len(s) <= 1000:
    s = s + [x ^ 1 for x in s]
    
def duplication(x):
    return s[x]

q = int(input().strip())
for a0 in range(q):
    x = int(input().strip())
    result = duplication(x)
    print(result)
