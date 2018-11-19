func maxProfit1(prices []int) int {
  max := 0
  for i := 0; i<len(prices); i++ {
    for j := i; j<len(prices); j++ {
      if prices[j] - prices[i] > max {
        max = prices[j] - prices[i]
      }
    }
  }
  return max
}

func cross(prices []int, mid int) int {
  min := prices[mid-1]
  for i := mid-1; i >= 0; i-- {
    if prices[i] < min {
      min = prices[i]
    }
  }
  max := min
  for i := mid; i <len(prices); i++ {
    if prices[i] > max {
      max = prices[i]
    }
  }
  return max - min
}

func maxProfit2(prices []int) int {
  if len(prices) < 2 {
    return 0
  }
  mid := len(prices) / 2
  cross := cross(prices, mid)
  left := maxProfit(prices[:mid])
  right := maxProfit(prices[mid:])
  if cross > left && cross > right {
    return cross
  } else if left > right {
    return left
  } else {
    return right
  }
}

func maxProfit3(prices []int) int {
  min := int(math.MaxInt64)
  max := 0
  for _, p := range prices {
    if p < min {
      min = p
    }
    if p - min > max {
      max = p - min
    }
  }
  return max
}
