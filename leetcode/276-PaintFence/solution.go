type count struct {
    cont int
    brek int
}
func numWays(n int, k int) int {
    if n == 0 || k == 0 {
        return 0
    }
    prev := make([]count, k)
    for i := 0; i < k; i++ {
        prev[i].brek = 1
    }
    for i := 1; i < n; i++ {
        curr := make([]count, k)
        for j := 0; j < k; j++ {
            curr[j].cont = prev[j].brek
            curr[j].brek = 0
            for l := 0; l < k; l++ {
                if l == j {
                    continue
                }
                curr[j].brek += (prev[l].cont + prev[l].brek)
            }
        }
        prev = curr
    }
    sum := 0
    for _, c := range prev {
        sum += (c.cont + c.brek)
    }
    return sum
}
