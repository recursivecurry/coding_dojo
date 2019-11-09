impl Solution {
    pub fn max_increase_keeping_skyline(grid: Vec<Vec<i32>>) -> i32 {
        let width = grid[0].len();
        let height = grid.len();
        let mut top: Vec<i32> = vec![0; width];
        let mut side: Vec<i32> = vec![0; height];

        for (r, row) in grid.iter().enumerate() {
            for (c, b) in row.iter().enumerate() {
                if *b > top[c] {
                    top[c] = *b;
                }
                if *b > side[r] {
                    side[r] = *b;
                }
            }
        }

        let mut sum = 0;
        for (r, row) in grid.iter().enumerate() {
           for (c, b) in row.iter().enumerate() {
              sum += if top[c] < side[r] { top[c] - *b } else { side[r] - *b };
          }
        }
        return sum
    }
}
