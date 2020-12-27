#[derive(Debug)]
struct Solution {}

impl Solution {
    pub fn can_make_arithmetic_progression(mut arr: Vec<i32>) -> bool {
        // let mut arr = arr.clone();
        arr.sort();
        let diff = arr[1] - arr[0];
        let mut old = arr[1];
        for v in &arr[2..] {
            if v - old != diff {
                return false;
            }
            old = *v;
        }
        true
    }
}

fn main() {
    let input1 = vec![3, 5, 1];
    print!("{}", Solution::can_make_arithmetic_progression(input1));
}
