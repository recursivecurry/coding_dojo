impl Solution {
    pub fn can_place_flowers(flowerbed: Vec<i32>, n: i32) -> bool {
        let mut zero = 1;
        let mut count = 0;
        for p in flowerbed {
            if p == 0 {
                zero += 1;
            } else {
                count += if zero > 1 { (zero-1) / 2} else { 0 };
                zero = 0;
                if count >= n {
                    break
                }
            }
        }
        (count + zero / 2) >= n
    }
}

#[cfg(test)]
mod test {
    use super::Solution;

    #[test]
    fn test_con_place_flowers() {
        assert_eq!(
            Solution::can_place_flowers(vec![1, 0, 0, 0, 1], 1),
            true
        );
        assert_eq!(
            Solution::can_place_flowers(vec![1, 0, 0, 0, 1], 2),
            false
        );
        assert_eq!(
            Solution::can_place_flowers(vec![1, 0, 0, 0, 0, 1], 2),
            false
        );
    }
}
