# HACKERRANK: Floyd: City of Blinding Lights
# https://www.hackerrank.com/challenges/floyd-city-of-blinding-lights
from collections import namedtuple
import heapq as h

Edge = namedtuple('Edge', ['cost', 'src', 'dest'])


def solve(paths, src, dest, cache = {}):
    if (src, dest) in cache:
        return cache[(src, dest)]

    passed = set([])
    next_queue = [Edge(0, src, src)]

    while next_queue:
        path = h.heappop(next_queue)
        if path.dest in passed:
            continue
        else:
            cache[(src, path.dest)] = path.cost
            passed.add(path.dest)

        for next_dest, next_cost in paths[path.dest].items():
            h.heappush(next_queue, Edge(cache[(src, path.dest)]+next_cost, path.dest,  next_dest))

    return cache[(src, dest)] if (src, dest) in cache else -1



def main():
    N, M = tuple(int(v) for v in input().split())
    paths = tuple({} for _ in range(N+1))
    for _ in range(M):
        src, dest, cost = tuple(int(v) for v in input().split())
        paths[src][dest] = cost
    Q = int(input())
    for _ in range(Q):
        src, dest = tuple(int(v) for v in input().split())
        print(solve(paths, src, dest))


if __name__ == '__main__':
    main()
