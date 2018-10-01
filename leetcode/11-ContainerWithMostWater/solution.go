func maxArea(height []int) int {
    max := 0
    left, right := 0, len(height)-1
    for left < right {
        max = Max(max, (right - left) * Min(height[left], height[right]))
        if height[left] < height[right] {
            left += 1
        } else {
            right -= 1
        }
    }
    return max
}

func Max(a, b int) int {
    if a < b {
        return b
    }
    return a
}

func Min(a, b int) int {
    if a < b {
        return a
    }
    return b
}
