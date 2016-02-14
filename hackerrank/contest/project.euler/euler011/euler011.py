def get_points(grid, r, c):
    m = 0
    if r < 17:
        v = grid[r][c]*grid[r+1][c]*grid[r+2][c]*grid[r+3][c]
        m = v if m < v else m
    if c < 17:
        v = grid[r][c]*grid[r][c+1]*grid[r][c+2]*grid[r][c+3]
        m = v if m < v else m
    if r < 17 and c < 17:
        v = grid[r][c]*grid[r+1][c+1]*grid[r+2][c+2]*grid[r+3][c+3]
        m = v if m < v else m
    if r < 17 and 2 < c:
        v = grid[r][c]*grid[r+1][c-1]*grid[r+2][c-2]*grid[r+3][c-3]
        m = v if m < v else m
    return m

if __name__ == "__main__":
    grid = tuple(tuple(int(v) for v in input().split()) for _ in range(20))
    print(max(tuple(get_points(grid, r, c) for r in range(20) for c in range(20))))
