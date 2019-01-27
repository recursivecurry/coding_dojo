func maxDistance(arrays [][]int) int {
  var min1 = [2]int{0, arrays[0][0]}
  var min2 = [2]int{0, arrays[0][len(arrays[0])-1]}
  var max1 [2]int
  var max2 [2]int
  
  if arrays[0][0] < arrays[1][0] {
    min1 = [2]int{0, arrays[0][0]}
    min2 = [2]int{1, arrays[1][0]}
  } else {
    min1 = [2]int{1, arrays[1][0]}
    min2 = [2]int{0, arrays[0][0]}
  }
  if arrays[0][len(arrays[0])-1] < arrays[1][len(arrays[1])-1] {
    max1 = [2]int{1, arrays[1][len(arrays[1])-1]}
    max2 = [2]int{0, arrays[0][len(arrays[0])-1]}
  } else {
    max1 = [2]int{0, arrays[0][len(arrays[0])-1]}
    max2 = [2]int{1, arrays[1][len(arrays[1])-1]}
  }
  
  for i, array := range arrays[2:] {
    if array[0] < min1[1] {
      min2 = min1
      min1 = [2]int{i+2, array[0]}
    } else if array[0] < min2[1] {
      min2 = [2]int{i+2, array[0]}
    }
    if array[len(array)-1] > max1[1] {
      max2 = max1
      max1 = [2]int{i+2, array[len(array)-1]}
    } else if array[len(array)-1] > max2[1] {
      max2 = [2]int{i+2, array[len(array)-1]}
    }
  }

  if min1[0] != max1[0] {
    return max1[1] - min1[1]
  } else if max2[1] - min1[1] > max1[1] - min2[1] {
    return max2[1] - min1[1]
  }
  return max1[1] - min2[1]
}
