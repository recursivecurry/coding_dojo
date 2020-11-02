// use std::collections::HashMap;

#[derive(Debug)]
struct Solution {}

impl Solution {
    pub fn is_alien_sorted(words: Vec<String>, order: String) -> bool {
        // let dict = order.chars().zip("abcdefghijklmnopqrstuvwxyz".chars()).collect::<HashMap<char, char>>();
        let mut dict2: Vec<char> = vec![' '; 26];
        order.chars().enumerate().for_each(|(i, v)| {
            dict2[(v as u8 - 'a' as u8) as usize] = ('a' as u8 + i as u8) as char;
        });
        let sorted = words.iter().map(|a| {
            a.chars().map(|c| {
                // dict.get(&c).unwrap()
                dict2.get((c as u8 - 'a' as u8) as usize).unwrap()
            }).collect::<String>()
        }).collect::<Vec<String>>();

        sorted.windows(2).all(|w| w[0] <= w[1])
    }
}

fn main() {
    println!("{:?}", Solution::is_alien_sorted(vec![String::from("abc"), String::from("abcdef")], String::from("bacdefghijklmnopqrstuvwxzy")));
}
