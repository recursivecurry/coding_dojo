func sortArrayByParityII(A []int) []int {
  even, odd := 0, 1
  output := make([]int, len(A))
  for _, a := range A {
    if a % 2 == 0 {
      output[even] = a
      even += 2
    } else {
      output[odd] = a
      odd += 2
    }
  }
  return output
}
