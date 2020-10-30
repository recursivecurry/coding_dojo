use std::collections::HashMap;

// #[derive(Debug)]
// struct Solution {}


impl Solution {
    pub fn roman_to_int(s: String) -> i32 {
        let mut sum: i32 = 0;
        let table: HashMap<char, i32> = [
            ('I', 1),
            ('V', 5),
            ('X', 10),
            ('L', 50),
            ('C', 100),
            ('D', 500),
            ('M', 1000),
            (' ', 0)]
            .iter().cloned().collect();
        for (c, n) in s.chars().zip(s.chars().skip(1).chain(" ".chars())) {
            let cv = table.get(&c).unwrap();
            let nv = table.get(&n).unwrap();
            if cv >= nv {
                sum += cv;
            } else {
                sum -= cv;
            }
            // println!("{:?}", c);
        }
        return sum;

    }
}

// fn main() {
//     println!("{}", Solution::roman_to_int(String::from("IV")));
//     println!("{}", Solution::roman_to_int(String::from("IX")));
//     println!("{}", Solution::roman_to_int(String::from("VII")));
//     println!("{}", Solution::roman_to_int(String::from("VI")));
//     println!("{}", Solution::roman_to_int(String::from("LVIII")));
//     println!("{}", Solution::roman_to_int(String::from("MCMXCIV")));
//     println!("{}", Solution::roman_to_int(String::from("MCDLXXVI")));
// }
