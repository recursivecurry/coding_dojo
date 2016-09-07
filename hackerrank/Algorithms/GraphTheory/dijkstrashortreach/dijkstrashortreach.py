# HACKERRANK: Dijkstra: Shortest Reach 2
# https://www.hackerrank.com/challenges/dijkstrashortreach
import heapq
from collections import namedtuple


Item = namedtuple('Item', ['r', 'n'])

def solve(t, n, m, es, s):
    dist = [-1] * (n)
    dist[s-1] = 0
    bq = [Item(0, s)]
    heapq.heapify(bq)
    while 0 < len(bq):
        current = heapq.heappop(bq)
        print(current)
        for item in es[current.n]:
            if dist[item.n-1] == -1 or dist[item.n-1] > dist[current.n-1] + item.r:
                dist[item.n-1] = dist[current.n-1] + item.r
                heapq.heappush(bq, Item(item.r, item.n))
    return(dist[:(s-1)]+dist[s:])


def main():
    N, M = (int(v) for v in input().split())
    ES = [set() for _ in range(N+1)]
    for _ in range(M):
        start, end, r = (int(v) for v in input().split())
        ES[start].add(Item(r, end))
        ES[end].add(Item(r, start))
    S = int(input())
    print(" ".join(map(lambda x: str(x), solve(T, N, M, ES, S))))

if __name__ == "__main__":
    T = int(input())
    for _ in range(T):
        main()
