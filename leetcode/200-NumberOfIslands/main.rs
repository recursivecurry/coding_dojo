struct Solution;

impl Solution {
    pub fn num_island(grid: Vec<Vec<char>>) -> i32 {
        struct Pos(usize, usize);

        let mut grid = grid;
        let mut candidate = Vec::new();
        let mut count = 0;
        let (width, height) = (grid[0].len(), grid.len());

        for x in 0..width {
            for y in 0..height {
                if grid[y][x] == '1' {
                    candidate.push(Pos(x, y));
                    while let Some(Pos(cx, cy)) = candidate.pop() {
                        grid[cy][cx] = '0';
                        if cx > 0 && grid[cy][cx - 1] == '1' {
                            candidate.push(Pos(cx - 1, cy))
                        }
                        if cy > 0 && grid[cy - 1][cx] == '1' {
                            candidate.push(Pos(cx, cy - 1))
                        }
                        if (cx + 1) < width && grid[cy][cx + 1] == '1' {
                            candidate.push(Pos(cx + 1, cy))
                        }
                        if (cy + 1) < height && grid[cy + 1][cx] == '1' {
                            candidate.push(Pos(cx, cy + 1))
                        }
                    }
                    count += 1;
                }
            }
        }
        count
    }
}

fn main() {}

#[cfg(test)]
mod test {
    use super::Solution;

    #[test]
    fn test_num_island() {
        assert_eq!(
            Solution::num_island(
                vec![
                    vec!['1','1','1','1','0'],
                    vec!['1','1','0','1','0'],
                    vec!['1','1','0','0','0'],
                    vec!['0','0','0','0','0'],
                ]
            ),
            1
        );
        assert_eq!(
            Solution::num_island(
                vec![
                    vec!['1','1','0','0','0'],
                    vec!['1','1','0','0','0'],
                    vec!['0','0','1','0','0'],
                    vec!['0','0','0','1','1'],
                ]
            ),
            3
        );
        assert_eq!(
            Solution::num_island(
                vec![
                    vec!['1','1','1'],
                    vec!['0','1','0'],
                    vec!['1','1','1'],
                ]
            ),
            1
        );
    }
}
