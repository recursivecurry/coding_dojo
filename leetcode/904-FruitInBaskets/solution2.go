func totalFruit(tree []int) int {
  var basket = make(map[int]int)
  var max int
  var count int
  var this = -1
  var that = -1
  
  for _, t := range tree {
    switch len(basket) {
    case 0:
        basket[t] = 1
    case 1:
      if _, ok := basket[t]; ok {
        basket[t]++
      } else {
        basket[t] = 1
      }
    default:
      if _, ok := basket[t]; ok {
        basket[t]++
      } else {
        delete(basket, that)
        basket[this] = count
        basket[t] = 1
      }
    }
    sum := 0
    for _, v := range basket {
      sum += v
    }
    if sum > max {
      max = sum
    }
    if this == t {
      count++
    } else {
      that = this
      this = t
      count = 1
    }
  }
  return max
}
