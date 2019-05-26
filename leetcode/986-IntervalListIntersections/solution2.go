func intervalIntersection(A [][]int, B [][]int) [][]int {
  iss := make([][]int, 0)
  for ai, bi := 0, 0; ai < len(A) && bi < len(B); {
    lo := A[ai][0]
    if B[bi][0] > lo {
      lo = B[bi][0]
    }
    hi := A[ai][1]
    if B[bi][1] < hi {
      hi = B[bi][1]
    }
    if lo <= hi {
      iss = append(iss, []int{lo, hi})
    }
    if A[ai][1] < B[bi][1] {
      ai++
    } else {
      bi++
    }
  }
  return iss
}
