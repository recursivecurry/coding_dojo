func hammingWeight(num uint32) int {
  var count int
  for num > 0 {
    if num % 2 == 1 {
      count++
    }
    num /= 2
  }
  return count
}
