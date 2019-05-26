func intervalIntersection(A [][]int, B [][]int) [][]int {
  iss := make([][]int, 0)
  for ai, bi := 0, 0; ai < len(A) && bi < len(B); {
    if A[ai][0] < B[bi][0] {
      if A[ai][1] < B[bi][0] {
        ai += 1
      } else if A[ai][1] <= B[bi][1] {
        iss = append(iss, []int{B[bi][0], A[ai][1]})
        ai += 1
      } else {
        iss = append(iss, []int{B[bi][0], B[bi][1]})
        bi += 1
      }
    } else if A[ai][0] == B[bi][0] {
      if A[ai][1] < B[bi][1] {
        iss = append(iss, []int{A[ai][0], A[ai][1]})
        ai += 1
      } else if A[ai][1] == B[bi][1] {
        iss = append(iss, []int{A[ai][0], A[ai][1]})
        ai += 1
        bi += 1
      } else {
        iss = append(iss, []int{A[ai][0], B[bi][1]})
        bi += 1
      }
    } else {
      if B[bi][1] < A[ai][0] {
        bi += 1
      } else if B[bi][1] <= A[ai][1] {
        iss = append(iss, []int{A[ai][0], B[bi][1]})
        bi += 1
      } else {
        iss = append(iss, []int{A[ai][0], A[ai][1]})
        ai += 1
      }
    }
  }
  return iss
}
