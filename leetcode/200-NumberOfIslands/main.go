const L byte = '1'
const W byte = '0'
const E byte = ' '
var Dirs = []Pos{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}

type Pos struct {
	Row int
	Col int
}

func isValid(grid [][]byte, pos Pos) bool {
	width := len(grid[0])
	height := len(grid)
	if pos.Row < 0 || pos.Row >= height {
		return false
	}
	if pos.Col < 0 || pos.Col >= width {
		return false
	}
	return grid[pos.Row][pos.Col] != E
}

func numIslands(grid [][]byte) int {
	if len(grid) == 0 ||  len(grid[0]) == 0 {
		return 0
	}
	positions := []Pos{Pos{0, 0}}
	count := 0
	for len(positions) > 0 {
		cur := positions[0]
		positions = positions[1:]
		kind := grid[cur.Row][cur.Col]
		switch kind {
		case W:
			grid[cur.Row][cur.Col] = E
			for _, d := range Dirs {
				next := Pos{cur.Row + d.Row, cur.Col + d.Col}
				if isValid(grid, next) {
					positions = append(positions, next)
				}
			}
		case L:
			grid[cur.Row][cur.Col] = E
			lands := []Pos{cur}
			for len(lands) > 0 {
				land := lands[0]
				lands = lands[1:]
				for _, d := range Dirs {
					next := Pos{land.Row + d.Row, land.Col + d.Col}
					if isValid(grid, next) {
						if grid[next.Row][next.Col] == L {
							lands = append(lands, next)
							grid[next.Row][next.Col] = E
						} else {
							positions = append(positions, next)
						}
					}
				}
			}
			count++
		case E:
		}
	}
	return count
}
