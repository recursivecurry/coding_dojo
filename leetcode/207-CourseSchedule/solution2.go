func canFinish(numCourses int, prerequisites [][]int) bool {
  deps := make([][]int, numCourses)
  for _, p := range prerequisites {
    deps[p[0]] = append(deps[p[0]], p[1])
  }
  courses := make([]int, numCourses)
  for i:=0; i < numCourses; i++ {
    if !dfs(i, deps, courses) {
      return false
    }
  }
  return true
}

func dfs(current int, deps [][]int, states []int) bool {
  if states[current] != 0 {
    return states[current] == 2
  }
  
  states[current] = 1
  for _, n := range deps[current] {
    if !dfs(n, deps, states) {
      return false
    }
  }
  states[current] = 2
  return true
}
