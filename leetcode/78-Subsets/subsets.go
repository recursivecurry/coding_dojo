func subsets(nums []int) [][]int {
    ss := [][]int{{}}
    for _, n := range nums {
        temp := [][]int{}
        for _, s := range ss {
            temp = append(temp, s)
            s2 := make([]int, len(s), len(s)+1)
            copy(s2, s)
            s2 = append(s2, n)
            temp = append(temp, s2)
        }
        ss = temp
    }
    return ss
}
