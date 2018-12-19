func hammingDistance(x int, y int) int {
  diff := x ^ y
  count := 0
  for diff > 0 {
    if diff % 2 == 1 {
      count++
    }
    diff /= 2
  }
  return count
}
