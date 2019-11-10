impl Solution {
    pub fn remove_vowels(s: String) -> String {
        let mut ans = String::new();
       for c in s.chars() {
          match c {
                'a' | 'e' | 'i' | 'o' | 'u' => {},
               _ => ans.push(c),
         };
       }
       ans
    }
}
