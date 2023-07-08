use std::mem;

struct Solution;

impl Solution {
    pub fn gcd_of_strings(str1: String, str2: String) -> String {
        fn check(s: &str, x: &str) -> bool {
            let si = s.chars();
            let xi = x.chars().cycle();
            si.zip(xi).map(|(sc, xc)| sc == xc).all(|v| v)
        }
        fn gcd(mut a: usize, mut b: usize) -> usize {
            while b != 0 {
                a = a % b;
                std::mem::swap(&mut a, &mut b)
            }
            a
        }
        let n = gcd(str1.len(), str2.len());
        for l in (1..=n).rev() {
            let x = &str1.as_str()[..l];
            if check(&str1, x) && check(&str2, x) {
                return x.to_string();
            }
        }
        String::from("")
    }
}

fn main() {

}

#[cfg(test)]
mod test {
    use super::Solution;

    #[test]
    fn test_gcd_of_strings() {
        assert_eq!(
            Solution::gcd_of_strings("ABCABC".to_string(), "ABC".to_string()),
            "ABC".to_string()
        );
        assert_eq!(
            Solution::gcd_of_strings("ABABAB".to_string(), "ABAB".to_string()),
            "AB".to_string()
        );
        assert_eq!(
            Solution::gcd_of_strings("LEET".to_string(), "CODE".to_string()),
            "".to_string()
        );
        assert_eq!(
            Solution::gcd_of_strings("AA".to_string(), "A".to_string()),
            "A".to_string()
        );
    }
}
