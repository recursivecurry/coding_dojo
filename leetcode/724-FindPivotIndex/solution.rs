impl Solution {
    pub fn pivot_index(nums: Vec<i32>) -> i32 {
        let sum: i32 = nums.iter().sum();
        let mut acc = 0;
        for (i, n) in nums.iter().enumerate() {
            if sum - n == acc + acc {
                return i as i32;
            } else {
                acc += n;
            }
        }
        -1
    }
}
