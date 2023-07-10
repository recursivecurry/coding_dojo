struct Solution;

impl Solution {
    pub fn kids_with_candies(candies: Vec<i32>, extra_candies: i32) -> Vec<bool> {
        let max = candies.iter().max().unwrap_or(&0);
        eprintln!("max: {}", *max);
        candies.iter().map(|n| (n+extra_candies) - max >= 0).collect()
    }
}

fn main() {
}

#[cfg(test)]
mod test {
    use super::Solution;

    #[test]
    fn test_kids_with_candies() {
        assert_eq!(
            Solution::kids_with_candies(vec![2, 3, 5, 1, 3], 3),
            vec![true, true, true, false, true]
        );
        assert_eq!(
            Solution::kids_with_candies(vec![4, 2, 1, 1, 2], 1),
            vec![true, false, false, false, false]
        );
        assert_eq!(
            Solution::kids_with_candies(vec![12, 1, 12], 10),
            vec![true, false, true]
        );
    }
}
