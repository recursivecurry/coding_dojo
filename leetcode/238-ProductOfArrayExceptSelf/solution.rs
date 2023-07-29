impl Solution {
    pub fn product_except_self(nums: Vec<i32>) -> Vec<i32> {
        let mut result = Vec::with_capacity(nums.len());
        let mut prev = 1;
        for i in 0..nums.len() {
            result.push(prev);
            prev *= nums[i];
        }
        let mut back = 1;
        for i in (0..nums.len()).rev() {
            result[i] *= back;
            back *= nums[i];
        }
        result
    }
}

#[cfg(test)]
mod test {
    use super::Solution;

    #[test]
    fn test_product_except_self() {
        assert_eq!(
            Solution::product_except_self(vec![1, 2, 3, 4]),
            vec![24, 12, 8, 6]
        );
        assert_eq!(
            Solution::product_except_self(vec![-1, 1, 0, -3, 3]),
            vec![0, 0, 9, 0, 0]
        );
        assert_eq!(
            Solution::product_except_self(vec![-1, 0, 0, -3, 3]),
            vec![0, 0, 0, 0, 0]
        );
    }
}
