impl Solution {
    pub fn max_vowels(s: String, k: i32) -> i32 {
        #[inline(always)]
        fn is_vowel(c: char) -> bool {
            matches!(c, 'a' | 'e' | 'i' | 'o' | 'u')
        }
        let mut end_iter = s.chars().into_iter();
        let mut begin_iter = s.chars().into_iter();
        let mut count = 0;
        let mut max = 0;
        for _ in 0..k {
            if is_vowel(end_iter.next().unwrap()) {
                count += 1;
            }
        }
        max = count;
        while let Some(e) = end_iter.next() {
            let b = begin_iter.next().unwrap();
            if is_vowel(e) {
                count += 1;
            }
            if is_vowel(b) {
                count -= 1;
            }
            if count > max {
                max = count;
            }
        }
        max
    }
}
