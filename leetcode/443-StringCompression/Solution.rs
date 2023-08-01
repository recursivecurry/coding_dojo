impl Solution {
    pub fn compress(chars: &mut Vec<char>) -> i32 {
        fn fill(chars: &mut Vec<char>, mut pos: usize, ch: char, count: usize) -> usize {
            chars[pos] = ch;
            pos += 1;
            if count != 1 {
                let num = count.to_string();
                for ch in num.chars() {
                    chars[pos] = ch;
                    pos += 1;
                }
            }
            pos
        }
        let mut pos: usize = 0;
        let mut ch: char = chars[0];
        let mut count = 1;
        for i in 1..chars.len() {
            if chars[i] == ch {
                count += 1;
            } else {
                pos = fill(chars, pos, ch, count);
                ch = chars[i];
                count = 1;
            }
        }
        fill(chars, pos, ch, count) as i32
    }
}
