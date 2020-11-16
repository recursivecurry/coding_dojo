#[derive(Debug)]
struct Solution {}

impl Solution {
    pub fn move_zeroes(nums: &mut Vec<i32>) {
        let mut pos: usize = 0;
        for i in 0..nums.len() {
            if nums[i] != 0 {
                nums[pos] = nums[i];
                pos += 1;
            }
        }
        for i in pos..nums.len() {
            nums[i] = 0;
        }
    }
}

fn main() {
    Solution::move_zeroes(&mut vec!(0, 1, 0, 3, 12));
}
