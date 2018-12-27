func search(nums []int, target int) int {
  if len(nums) == 0 {
    return -1
  }
  low := 0
  high := len(nums) - 1
  for low < high {
    mid := (low + high) / 2
    if nums[mid] > nums[high] {
      low = mid + 1
    } else {
      high = mid
    }
  }
  rotate := low
  fn := func(i int) int { return (i + rotate) % len(nums)}
  low = 0
  high = len(nums)-1
  for low < high {
    mid := (low + high) / 2
    if nums[fn(mid)] < target {
      low = mid + 1
    } else {
      high = mid
    }
  }
  if nums[fn(low)] == target {
    return fn(low)
  } else {
    return -1
  }
}

func search2(nums []int, target int) int {
  m:=make(map[int]int)
  for i:=0;i<len(nums);i++{
    m[nums[i]]=i
  }
  if v,ok:=m[target];ok{
    return v
  } else {
    return -1
  }
}
