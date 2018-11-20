func shortestDistance(words []string, word1 string, word2 string) int {
  w1, w2 := -1, -1
  shortest := len(words)
  for i, w := range words {
    if w == word1 {
      w1 = i
      if w2 != -1 {
        if w1 - w2 < shortest {
          shortest = w1 - w2
        }
      }
    } else if w == word2 {
      w2 = i
      if w1 != -1 {
        if w2 - w1 < shortest {
          shortest = w2 - w1
        }
      }
    }
  }
  return shortest
}
