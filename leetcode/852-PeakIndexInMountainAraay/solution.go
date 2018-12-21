func peakIndexInMountainArray(A []int) int {
  var climb bool
  var floor int
  var peak int
  if A[0] < A[1] {
    climb = true
    floor = 0
    peak = -1
  } else {
    climb = false
    floor = 0
    peak = -1
  }
  for i:=2; i<len(A); i++ {
    if floor < peak && floor + 2 <= i {
      break
    }
    if climb {
      if A[i-1] > A[i] {
        peak = i-1
        climb = false
      }
    } else {
      if A[i-1] < A[i] {
        climb = true
        floor = i-1
      }
    } 
  }
  return peak
}
