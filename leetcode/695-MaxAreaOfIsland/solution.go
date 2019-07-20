func maxAreaOfIsland(grid [][]int) int {
  rn := len(grid)
  if rn == 0 {
    return 0
  }
  cn := len(grid[0])
  if cn == 0 {
    return 0
  }
  union := make([]int, 0)
  weight := make([]int, 0)
  
  for r := 0; r < rn; r++ {
    for c := 0; c < cn; c++ {
      if grid[r][c] == 1 {
        union = append(union, r * cn + c)
        weight = append(weight, 1)
      } else {
        union = append(union, r * cn + c)
        weight = append(weight, 0)
      }
    }
  }
  
  root := func(r1, c1 int) int {
    r := r1 * cn + c1
    
    for union[r] != r {
      r = union[r]
    }
    return r
  }
  
  connect := func(r1, c1, r2, c2 int) {
    root1 := root(r1, c1)
    root2 := root(r2, c2)
    
    if root1 == root2 {
      return
    }
    
    union[root1] = root2
    weight[root2] += weight[root1]
  }
  for r := 0; r < rn; r++ {
    for c := 0; c < cn; c++ {
      if grid[r][c] == 1 {
        if c > 0 && grid[r][c-1] == 1 {
          connect(r, c, r, c-1)
        }
        if r > 0 && grid[r-1][c] == 1 {
          connect(r, c, r-1, c)
        }
      }
    }
  }
  max := 0
  for _, c := range weight {
    if c > max {
      max = c
    }
  }
  return max
}
