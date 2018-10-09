func minCost(costs [][]int) int {
    prev := [3]int{0, 0, 0}
    for _, c := range costs {
        c[0] = min(c[0]+prev[1], c[0]+prev[2])
        c[1] = min(c[1]+prev[0], c[1]+prev[2])
        c[2] = min(c[2]+prev[0], c[2]+prev[1])
        prev[0], prev[1], prev[2] = c[0], c[1], c[2]
    }
    return min(prev[0], min(prev[1], prev[2]))
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}
