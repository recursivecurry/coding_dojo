func sortedSquares(A []int) []int {
  var output []int
  output = make([]int, 0, len(A))
  pos := sort.Search(len(A)-1, func(i int) bool {
    return A[i] >= 0
  })
  if A[pos] < 0 {
    pos = len(A)
  }
  neg := pos - 1
  for neg >= 0 && pos < len(A) {
    if -A[neg] < A[pos] {
      output = append(output, A[neg] * A[neg])
      neg--
    } else {
      output = append(output, A[pos] * A[pos])
      pos++
    }
  }
  for neg >= 0 {
    output = append(output, A[neg] * A[neg])
    neg--
  }
  for pos < len(A) {
    output = append(output, A[pos] * A[pos])
    pos++
  }
  return output
}
