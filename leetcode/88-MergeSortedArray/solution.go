func merge(nums1 []int, m int, nums2 []int, n int)  {
  for o := m + n; o > 0; o-- {
    if m == 0 {
      nums1[o-1] = nums2[n-1]
      n--
    } else if n == 0 {
      nums1[o-1] = nums1[m-1]
      m--
    } else if nums1[m-1] > nums2[n-1] {
      nums1[o-1] = nums1[m-1]
      m--
    } else {
      nums1[o-1] = nums2[n-1]
      n--
    }
  }
}
