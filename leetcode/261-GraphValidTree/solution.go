func validTree(n int, edges [][]int) bool {
    if n == 0 {
        return false
    }
    m := make(map[int]map[int]struct{}, n)
    for i := 0; i < n; i++ {
        m[i] = make(map[int]struct{})
    }
    for _, e := range edges {
        m[e[0]][e[1]] = struct{}{}
        m[e[1]][e[0]] = struct{}{}
    }
    passed := make(map[int]struct{})
    return dfs(m, passed, 0) && len(passed) == n
}

func dfs(m map[int]map[int]struct{}, passed map[int]struct{}, current int) bool {
    if _, ok := passed[current]; ok {
        return false
    }
    passed[current] = struct{}{}
    for d, _ := range m[current] {
        delete(m[current], d)
        delete(m[d], current)
        if dfs(m, passed, d) == false {
            return false
        }
    }
    return true
}
