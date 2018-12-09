func totalFruit(tree []int) int {
  if len(tree) == 0 {
    return 0
  }
  var basket = [2]int{-1, tree[0]}
  var right int
  var left int
  var current int
  var max int
  
  for right = 1; right < len(tree); right++ {
    if tree[right] == basket[1] {
    } else if tree[right] == basket[0] {
      basket[0], basket[1] = basket[1], tree[right]
      current = right
    } else if basket[0] == -1 {
      basket[0], basket[1] = basket[1], tree[right]
      current = right
    } else {
      basket[0], basket[1] = basket[1], tree[right]
      count := right - left
      if count > max {
        max = count
      }
      left = current
      current = right
    }
  }
  count := right - left
  if count > max {
    max = count
  }
  return max
}
