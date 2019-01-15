func intersection(nums1 []int, nums2 []int) []int {
  ns1 := make(map[int]struct{})
  ns2 := make(map[int]struct{})
  for _, n := range nums1 {
    ns1[n] = struct{}{}
  }
  for _, n := range nums2 {
    ns2[n] = struct{}{}
  }
  output := []int{}
  for n, _ := range ns1 {
    if _, ok := ns2[n]; ok {
      output = append(output, n)
    }
  }
  return output
}
