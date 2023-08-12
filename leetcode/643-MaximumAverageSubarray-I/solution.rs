impl Solution {
    pub fn find_max_average(nums: Vec<i32>, k: i32) -> f64 {
        let mut end_iter = nums.iter();
        let mut begin_iter = nums.iter();
        let mut sum = 0;
        let mut max = 0;
        for _ in 0..k {
            sum += end_iter.next().unwrap();
        }
        max = sum;
        while let Some(e) = end_iter.next() {
            let b = begin_iter.next().unwrap();
            sum = sum + e - b;
            if sum > max {
                max = sum;
            }
        }
        max as f64 / k as f64
    }
}
