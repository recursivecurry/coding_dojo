func lengthOfLIS(nums []int) int {
  dp := make([]int, 0)
  for _, n := range nums {
    idx := sort.SearchInts(dp, n)
    if idx == len(dp) {
      dp = append(dp, n)
    } else {
      dp[idx] = n
    }
  }
  return len(dp)
}

func lengthOfLIS2(nums []int) int {
  if len(nums) == 0 {
    return 0
  }
  count := make([]int, len(nums))
  max := 0
  for i:=1;i<len(nums);i++ {
    for j:=0; j<i; j++ {
      if nums[i] > nums[j] && count[j] + 1 > count[i] {
        count[i] = count[j] + 1
        //fmt.Println(j, i, count[j], count[i])
        if count[i] > max {
          max = count[i]
        }
      }
    }
  }
  return max + 1
}
