impl Solution {
    pub fn max_operations(mut nums: Vec<i32>, k: i32) -> i32 {
        nums.sort_unstable();
        let mut count = 0;
        let mut left = 0;
        let mut right = nums.len() - 1;
        while left < right {
            let lv = nums[left];
            let rv = nums[right];
            if lv + rv < k {
                left += 1;
            } else if lv + rv > k {
                right -= 1;
            } else {
                count += 1;
                left += 1;
                right -= 1;
            }
        }
        count
    }
}
