import functools
relation = set()
kind = 2
row = int(input())
col = int(input())
m = [[int(v) for v in input().split(' ')] for _ in range(row)]
for r, c in [(r, c) for r in range(row) for c in range(col)]:
    if m[r][c] == 1:
        if 0 < r:
            if 0 < c:
                if 1 < m[r-1][c-1]:
                    m[r][c] = m[r-1][c-1]
                else:
                    m[r][c] = kind
                    kind += 1
            if c < col-1:
                if 1 < m[r-1][c+1]:
                    if 1 < m[r][c]:
                        if m[r-1][c+1] != m[r][c]:
                            rel.add((min(m[r][c], m[r-1][c+1]), max(m[r][c], m[r-1][c+1])))
                    else:
                        m[r][c] = m[r-1][c+1]
                else:
                    m[r][c] = kind
                    kind += 1
            if 1 < m[r-1][c]:
                if 1 < m[r-1][c]:
                    if 1 < m[r][c]:
                        if m[r-1][c] != m[r][c]:
                            rel.add((min(m[r][c], m[r-1][c]), max(m[r][c], m[r-1][c])))
                    else:
                        m[r][c] = m[r-1][c]
                else:
                    m[r][c] = kind
                    kind += 1
        else:
            if 0 < c:
                if 1 < m[r][c-1]:
                    if m[r][c-1] != m[r][c]:
                        rel.add((min(m[r][c], m[r][c-1]), max(m[r][c], m[r][c-1])))
                else:
                    m[r][c] = m[r][c-1]
            else:
                m[r][c] = kind
                kind += 1
for
                
                
