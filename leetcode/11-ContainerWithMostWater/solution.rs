impl Solution {
    pub fn max_area(height: Vec<i32>) -> i32 {
        let mut left= 0;
        let mut right= height.len()-1;

        let mut max = 0;

        while left < right {
            let width = (right - left) as i32;
            let lh = height[left];
            let rh = height[right];
            if lh <= rh {
                if max < lh * width {
                    max = lh * width;
                }
                left += 1;
            } else {
                if max < rh * width {
                    max = rh * width;
                }
                right -= 1;
            }
        }
        max
    }
}
