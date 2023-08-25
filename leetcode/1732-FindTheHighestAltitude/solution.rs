impl Solution {
    pub fn largest_altitude(gain: Vec<i32>) -> i32 {
        gain.iter().fold((0, 0), |(m, c), n| { if c + n > m { (c+n, c+n) } else { (m, c+n) } }).0
    }
}
