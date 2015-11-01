# HACKERRANK: Prim's (MST): Special Subtree
# https://www.hackerrank.com/challenges/primsmstsub
from collections import namedtuple
import heapq


Edge = namedtuple('Edge', ['cost', 'dest'])


def solve(n, m, edge_list, s):
    """
    >>> solve(5, 6, [set(), {Edge(cost=3, dest=2), Edge(cost=4, dest=3)}, {Edge(cost=6, dest=4), Edge(cost=2, dest=5), Edge(cost=3, dest=1), Edge(cost=5, dest=3)}, {Edge(cost=7, dest=5), Edge(cost=4, dest=1), Edge(cost=5, dest=2)}, {Edge(cost=6, dest=2)}, {Edge(cost=7, dest=3), Edge(cost=2, dest=2)}], 1)
    15
    """
    next_edge_queue = []
    included_nodes = set([s])
    total_cost = 0

    for edge in edge_list[s]:
        heapq.heappush(next_edge_queue, edge)

    while len(included_nodes) < n:
        selected_node = None
        while selected_node == None:
            selected_node = heapq.heappop(next_edge_queue)
            if selected_node.dest in included_nodes:
                selected_node = None
            else:
                included_nodes.add(selected_node.dest)
                total_cost += selected_node.cost
        for dest in edge_list[selected_node.dest]:
            heapq.heappush(next_edge_queue, dest)
    return total_cost


def main():
    N, M = (int(v) for v in input().split())
    ES = [set() for _ in range(N+1)]
    for _ in range(M):
        v1, v2, cost = (int(v) for v in input().split())
        ES[v1].add(Edge(cost, v2))
        ES[v2].add(Edge(cost, v1))
    S = int(input())
    print(solve(N, M, ES, S))


if __name__ == "__main__":
    #import doctest
    #doctest.testmod()
    main()
