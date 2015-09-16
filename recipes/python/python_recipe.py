# INPUT

## multi value in one line

A, B = tuple(int(n) for n in input().split())

## pair values in multi lines

N = int(input())
PS = sorted(tuple( tuple(int(p) for p in  input().split()) for n in range(N)))


# SORTING

## key functions

from operator import attrgetter, itemgetter

a = {id: 1, name:'haha'}
attrgetter('name')(a) # => 'haha'

b = [1, 2, 3]
itemgetter(1)(b) # => 2
