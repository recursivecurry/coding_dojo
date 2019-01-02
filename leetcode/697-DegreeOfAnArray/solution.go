func findShortestSubArray(nums []int) int {
  counter := make(map[int][3]int)
  for i, n := range nums {
    c, ok := counter[n]
    if ok {
      counter[n] = [3]int{c[0]+1, c[1], i}
    } else {
      counter[n] = [3]int{1, i, i}
    }
  }
  m := 0
  l := len(nums)
  for _, c := range counter {
    if c[0] > m {
      m = c[0]
      l = c[2] - c[1] + 1
    } else if c[0] == m && c[2]-c[1]+1 < l {
      l = c[2] - c[1] + 1
    }
  }
  return l
}
