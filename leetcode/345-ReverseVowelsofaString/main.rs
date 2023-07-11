use std::collections::HashSet;

struct Solution;

impl Solution {
    pub fn reverse_vowels(s: String) -> String {
        #[inline]
        fn is_vowels(c: char) -> bool {
            c == 'a' ||  c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' ||  c == 'E' || c == 'I' || c == 'O' || c == 'U'
        }
        let mut reverse = s.chars().rev().filter(|&c| is_vowels(c));
        s.chars().into_iter().map(|c| if is_vowels(c) { reverse.next().unwrap()} else {c}).collect::<String>()
    }
}

#[cfg(test)]
mod test {
    use super::Solution;

    #[test]
    fn test_reverse_vowels() {
        assert_eq!(
            Solution::reverse_vowels("hello".to_string()),
            "holle".to_string()
        );
        assert_eq!(
            Solution::reverse_vowels("leetcode".to_string()),
            "leotcede".to_string()
        );
        assert_eq!(
            Solution::reverse_vowels("aA".to_string()),
            "Aa".to_string()
        );
    }
}
