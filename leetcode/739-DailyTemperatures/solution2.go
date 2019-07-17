func dailyTemperatures(T []int) []int {
  l := len(T)
  ans := make([]int, len(T))
  temp := make([]int, 101)
  for i := 0; i < len(temp); i++ {
    temp[i] = math.MaxInt64
  }
  for i := len(T)-1; i >= 0; i-- {
    c := T[i]
    p := l
    for t := c+1; t <= 100; t++ {
      if temp[t] < p {
        p = temp[t]
      }
    }
    if p < l {
      ans[i] = p - i
    }
    temp[T[i]] = i
  }
  return ans
}
