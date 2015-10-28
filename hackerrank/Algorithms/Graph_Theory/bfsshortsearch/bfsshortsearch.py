# HACKERRANK: Breadth First Search: Shortest Reach
# https://www.hackerrank.com/challenges/bfsshortreach
from collections import deque


def solve(t, n, m, es, s):
    dist = [-1] * (n)
    dist[s-1] = 0
    bq = deque([s])
    while 0 < len(bq):
        current = bq.popleft()
        for n in es[current]:
            if dist[n-1] == -1:
                dist[n-1] = dist[current-1] + 6
                bq.append(n)
    return(dist[:(s-1)]+dist[s:])


def main():
    N, M = (int(v) for v in input().split())
    ES = [set() for _ in range(N+1)]
    for _ in range(M):
        start, end = (int(v) for v in input().split())
        ES[start].add(end)
        ES[end].add(start)
    S = int(input())
    print(" ".join(map(lambda x: str(x), solve(T, N, M, ES, S))))

if __name__ == "__main__":
    T = int(input())
    for _ in range(T):
        main()
