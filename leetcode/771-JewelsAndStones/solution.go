func numJewelsInStones(J string, S string) int {
  var count int
  jewels := make(map[rune]struct{})
  for _, r := range J {
    jewels[r] = struct{}{}
  }
  for _, s := range S {
    if _, ok := jewels[s]; ok {
      count++
    }
  }
  return count
}
