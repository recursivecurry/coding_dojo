func magicalString(n int) int {
  size := n
  if size < 3 {
    size = 3
  }
  ns := append(make([]int, size))
  ns[0], ns[1], ns[2] = 1, 2, 2
  in, out, cur, prev, count := 0, 0, 1, 2, 0
  
  for out < n {
    for i := 0; i < ns[in] && out < n; i++  {
      ns[out] = cur
      if cur == 1 {
        count++
      }
      out++
    }
    in++
    cur, prev = prev, cur
  }
  return count
}
