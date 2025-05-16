struct Solution;

impl Solution {
    pub fn three_sum_closest(mut nums: Vec<i32>, target: i32) -> i32 {
        nums.sort_unstable();
        let mut closest_sum = nums[0] + nums[1] + nums[2];
        let n = nums.len();
        for i in 0..n - 2 {
            let mut left = i + 1;
            let mut right = nums.len() - 1;
            while left < right {
                let current_sum = nums[i] + nums[left] + nums[right];
                if (current_sum - target).abs() < (closest_sum - target).abs() {
                    closest_sum = current_sum;
                }
                if current_sum < target {
                    left += 1;
                } else if current_sum > target {
                    right -= 1;
                } else {
                    break
                }
            }
        }
        closest_sum
    }
}

fn main() {
    let nums = vec![-1, 2, 1, -4];
    let target = 1;
    let result = Solution::three_sum_closest(nums, target);
    println!("The closest sum is: {}", result);
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_three_sum_closest() {
        assert_eq!(Solution::three_sum_closest(vec![-1, 2, 1, -4], 1), 2);
        assert_eq!(Solution::three_sum_closest(vec![0, 0, 0], 1), 0);
    }
}
