# HACKERRANK: Kruskal (MST): Really Special Subtree
# https://www.hackerrank.com/challenges/kruskalmstrsub
from collections import namedtuple
import heapq

Edge = namedtuple('Edge', ['weight', 'dest'])


def get_detailed_weight(src, dest, weight, es):
    return (weight, len(es[src]) + len(es[dest]) + weight)


def solve(n, m, es, s):
    total_weight = 0
    included_set = set([s])
    next_edge_queue = []

    for edge in es[s]:
        heapq.heappush(next_edge_queue,
                       Edge(get_detailed_weight(s, edge.dest, edge.weight, es), edge.dest))

    while len(included_set) != n:
        next_edge = None
        while next_edge == None:
            next_edge = heapq.heappop(next_edge_queue)
            if next_edge.dest in included_set:
                next_edge = None
            else:
                total_weight += next_edge.weight[0]
                included_set.add(next_edge.dest)

        for edge in es[next_edge.dest]:
            heapq.heappush(next_edge_queue,
                           Edge(get_detailed_weight(next_edge[1], edge.dest, edge.weight, es),
                                edge.dest))

    return total_weight


def main():
    N, M = tuple(int(v) for v in input().split())
    ES = [set() for _ in range(N+1)]
    for _ in range(M):
        v1, v2, weight = tuple(int(v) for v in input().split())
        ES[v1].add(Edge(weight, v2))
        ES[v2].add(Edge(weight, v1))
    S = int(input())
    print(solve(N, M, ES, S))


if __name__ == "__main__":
    main()
